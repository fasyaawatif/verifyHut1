package com.example.verifyhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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

public class ViewManufacturer extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,SearchView.OnQueryTextListener{

    private static final String URL_MANUFACTURERS = "http://192.168.43.131/verifyhut/viewmanufacturer/api.php";
    private static final String url_cari = "http://192.168.43.131/verifyhut/search/cari_data2.php";

    private static final String TAG = MainActivity.class.getSimpleName();

    //public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_DESC = "description";
    public static final String TAG_ADDRESS = "address";
    public static final String TAG_PHONE = "phone";
    public static final String TAG_NAMA = "ssmID";
    public static final String TAG_RESULTS = "results";
    public static final String TAG_MESSAGE = "message";
    public static final String TAG_VALUE = "value";

    String tag_json_obj = "json_obj_req";

    List<listManufacturer> manufacturerList;
    RecyclerView recyclerView;
    ManufacturerAdapter adapter;

    //
    ProgressDialog pDialog;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_manufacturer);

        //
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipe.setOnRefreshListener(this);

        manufacturerList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewManufacturer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(false);
                           loadManufacturers();
                       }
                   }
        );
    }

    private void loadManufacturers(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_MANUFACTURERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray array = new JSONArray(response);

                            for(int i = 0; i<array.length(); i++){
                                JSONObject manufacturer = array.getJSONObject(i);

                                String mName = manufacturer.getString("name");
                                String mEmail = manufacturer.getString("email");
                                String mDesc = manufacturer.getString("description");
                                String mAddress = manufacturer.getString("address");
                                String mPhone = manufacturer.getString("phone");
                                String mID = manufacturer.getString("ssmID");

                                listManufacturer listManufacturer = new listManufacturer(mName, mEmail, mDesc, mAddress, mPhone, mID);
                                manufacturerList.add(listManufacturer);

                            }
                            adapter = new ManufacturerAdapter(ViewManufacturer.this, manufacturerList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                  public void onErrorResponse(VolleyError error){

                  }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        cariData(query);
        return false;
    }

    @Override
    public void onRefresh() {
        loadManufacturers();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.search_manufacturer, menu);
        final MenuItem item = menu.findItem(R.id.searchManufacturer);
        final SearchView searchView = (SearchView) item.getActionView();
        //searchView.setQueryHint(getString(R.string.type_name));
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    private void cariData(final String keyword) {
        pDialog = new ProgressDialog(ViewManufacturer.this);
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
                        manufacturerList.clear();
                        adapter.notifyDataSetChanged();

                        String getObject = jObj.getString(TAG_RESULTS);
                        JSONArray jsonArray = new JSONArray(getObject);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            listManufacturer data = new listManufacturer();

                            //data.setId(obj.getString(TAG_ID));
                            data.setName(obj.getString(TAG_NAME));
                            data.setEmail(obj.getString(TAG_EMAIL));
                            data.setDescription(obj.getString(TAG_DESC));
                            data.setAddress(obj.getString(TAG_ADDRESS));
                            data.setPhone(obj.getString(TAG_PHONE));
                            data.setSsmID(obj.getString(TAG_NAMA));

                            manufacturerList.add(data);
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
