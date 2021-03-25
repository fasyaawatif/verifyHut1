package com.example.verifyhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryG extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private static final String URL_PRODUCTS = "http://192.168.43.131/verifyhut/viewproduct/api8.php";
    //
    private static final String url_cari = "http://192.168.43.131/verifyhut/search/cari_data.php";

    private static final String TAG = CategoryG.class.getSimpleName();

    //public static final String TAG_ID = "id";
    public static final String TAG_NAME = "productName";
    public static final String TAG_BRAND = "brand";
    public static final String TAG_INGREDIENT = "ingredient";
    public static final String TAG_HALAL = "halalCertified";
    public static final String TAG_DESC = "productDesc";
    public static final String TAG_TYPE = "type";
    public static final String TAG_NAMA = "productID";
    public static final String TAG_RESULTS = "results";
    public static final String TAG_MESSAGE = "message";
    public static final String TAG_VALUE = "value";

    String tag_json_obj = "json_obj_req";



    List<listProduct2> productList = new ArrayList<>();
    RecyclerView recyclerView;
    ProductAdapter2 adapter;

    //
    ProgressDialog pDialog;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_g);
        //
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipe.setOnRefreshListener(this);


        //productList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewCategoryG);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(false);
                           loadProducts();
                       }
                   }
        );


    }

    private void loadProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject product = array.getJSONObject(i);

                                String pName = product.getString("productName");
                                String pBrand = product.getString("brand");
                                String pIngredient = product.getString("ingredient");
                                String pHalalCertified = product.getString("halalCertified");
                                String pDesc = product.getString("productDesc");
                                String pType = product.getString("type");
                                String pID = product.getString("productID");

                                listProduct2 listProduct2 = new listProduct2(pName, pBrand, pIngredient, pHalalCertified, pDesc, pType, pID);
                                productList.add(listProduct2);

                            }

                            adapter = new ProductAdapter2(CategoryG.this, productList);
                            recyclerView.setAdapter(adapter);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CategoryG.this,error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);

    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        //cariData(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        cariData(query);
        return false;
    }

    @Override
    public void onRefresh() {
        loadProducts();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.search_product, menu);
        final MenuItem item = menu.findItem(R.id.searchPrd);
        final SearchView searchView = (SearchView) item.getActionView();
        //searchView.setQueryHint(getString(R.string.type_name));
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }


    private void cariData(final String keyword) {
        pDialog = new ProgressDialog(CategoryG.this);
        pDialog.setCancelable(true);
        pDialog.setMessage("Loading...");
        //pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_cari, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Response: ", response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);

                    int value = jObj.getInt(TAG_VALUE);

                    if (value == 1) {
                        productList.clear();
                        adapter.notifyDataSetChanged();

                        String getObject = jObj.getString(TAG_RESULTS);
                        JSONArray jsonArray = new JSONArray(getObject);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            listProduct2 data = new listProduct2();

                            //data.setId(obj.getString(TAG_ID));
                            data.setProductName(obj.getString(TAG_NAME));
                            data.setBrand(obj.getString(TAG_BRAND));
                            data.setIngredient(obj.getString(TAG_INGREDIENT));
                            data.setHalalCertified(obj.getString(TAG_HALAL));
                            data.setProductDesc(obj.getString(TAG_DESC));
                            data.setType(obj.getString(TAG_TYPE));
                            data.setProductID(obj.getString(TAG_NAMA));

                            productList.add(data);
                        }

                    } else{
                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
                pDialog.dismiss();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("keyword", keyword);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}