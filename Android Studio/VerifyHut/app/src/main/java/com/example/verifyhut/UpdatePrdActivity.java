package com.example.verifyhut;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class UpdatePrdActivity extends AppCompatActivity {

    EditText prdID, prdName, prdBrand, prdDesc, prdIngredient, prdHalal;
    Spinner prdType;
    RequestQueue requestQueue;
    String prdIDHolder, prdNameHolder, prdBrandHolder, prdDescHolder, prdIngredientHolder, prdTypeHolder, prdHalalHolder;
    ProgressDialog progressDialog;
    String url = "http://192.168.43.131/verifyhut/product/updateProduct.php";
    String urlDelete = "http://192.168.43.131/verifyhut/product/deleteProduct.php";

    //private Menu action1, action2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prd);

        prdID = (EditText)findViewById(R.id.prdID);
        prdName = (EditText)findViewById(R.id.prdName);
        prdBrand = (EditText)findViewById(R.id.prdBrand);
        prdDesc = (EditText)findViewById(R.id.prdDesc);
        prdIngredient = (EditText)findViewById(R.id.prdIngredient);
        prdHalal = (EditText)findViewById(R.id.prdHalal);
        prdType = (Spinner) findViewById(R.id.prdType);

        requestQueue = Volley.newRequestQueue(UpdatePrdActivity.this);
        progressDialog = new ProgressDialog(UpdatePrdActivity.this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu edit){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_edit, edit);
        //menuInflater.inflate(R.menu.menu_edit, delete);
        //action1 = edit;
        //action2 = delete;
        //action1.findItem(R.id.editMenu).setVisible(false);
        //action2.findItem(R.id.deleteMenu).setVisible(false);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.editMenu){
            //Toast.makeText(UpdatePrdActivity.this, "Product Updated", Toast.LENGTH_SHORT).show();


                    progressDialog.setMessage("Please Wait, Product Is Inserting");
                    progressDialog.show();

                    GetValueFromEditText();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();
                                    Toast.makeText(UpdatePrdActivity.this, response, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(UpdatePrdActivity.this, ViewProduct.class);
                                    startActivity(intent);
                                    finish();
                                }

                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressDialog.dismiss();

                                    Toast.makeText(UpdatePrdActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {

                            // Creating Map String Params.
                            Map<String, String> params = new HashMap<String, String>();

                            // Adding All values to Params.
                            params.put("productID", prdIDHolder);
                            params.put("productName", prdNameHolder);
                            params.put("brand", prdBrandHolder);
                            params.put("productDesc", prdDescHolder);
                            params.put("type", prdTypeHolder);
                            params.put("ingredient", prdIngredientHolder);
                            params.put("halalCertified", prdHalalHolder);

                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(UpdatePrdActivity.this);
                    requestQueue.add(stringRequest);

        }

        if (item.getItemId() == R.id.deleteMenu){
            //Toast.makeText(UpdatePrdActivity.this, "Product Deleted", Toast.LENGTH_SHORT).show();

            progressDialog.setMessage("Please Wait, Product Is Deleting");
            progressDialog.show();

            GetValueFromEditText();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(UpdatePrdActivity.this, response, Toast.LENGTH_LONG).show();

                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();

                            Toast.makeText(UpdatePrdActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {

                    // Creating Map String Params.
                    Map<String, String> params = new HashMap<String, String>();

                    // Adding All values to Params.
                    params.put("productID", prdIDHolder);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(UpdatePrdActivity.this);
            requestQueue.add(stringRequest);
            Intent intent = new Intent(UpdatePrdActivity.this, ViewProduct.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    public void GetValueFromEditText() {
        prdIDHolder = prdID.getText().toString();
        prdNameHolder = prdName.getText().toString();
        prdBrandHolder = prdBrand.getText().toString();
        prdDescHolder = prdDesc.getText().toString();
        prdTypeHolder = prdType.getSelectedItem().toString();
        prdIngredientHolder = prdIngredient.getText().toString();
        prdHalalHolder = prdHalal.getText().toString();

    }
}
