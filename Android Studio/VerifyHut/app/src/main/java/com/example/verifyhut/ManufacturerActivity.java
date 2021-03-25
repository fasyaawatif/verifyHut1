package com.example.verifyhut;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ManufacturerActivity extends AppCompatActivity {

    EditText etUsername2, etPassword2;
    TextView registerBtn2, response2;
    Button signBtn2;
    private ImageView alertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturer);

        etUsername2 = (EditText)findViewById(R.id.ssmID);
        etPassword2 = (EditText)findViewById(R.id.password2);
        response2 = (TextView)findViewById(R.id.result2);
        registerBtn2 = (TextView)findViewById(R.id.btnRegisterManufacturer);
        signBtn2 = (Button)findViewById(R.id.btnSignManufacturer);
        alertButton = (ImageView) findViewById(R.id.btnInfo);


        registerBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });


        signBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(ManufacturerActivity.this);
                String url = "http://192.168.43.131/verifyhut/manufacturer/api.php";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                String jsonStr = response;
                                if (jsonStr!=null) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(jsonStr);
                                        String query_result = jsonObject.getString("query_result");
                                        if (query_result.equalsIgnoreCase("SUCCESS")) {
                                            Intent dashboard = new Intent(ManufacturerActivity.this, MnfHome.class);
                                            dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(dashboard);
                                            finish();
                                            //Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();

                                        }
                                        else if (query_result.equalsIgnoreCase("FAILED")) {
                                            Toast.makeText(ManufacturerActivity.this,"Failed Login",Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(ManufacturerActivity.this,"Error, parsing data",Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(ManufacturerActivity.this,"Couldn't access JSON Data",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        response2.setText("That didn't work!");
                    }
                }) {
                    //adding parameters to the request
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("method","login");
                        params.put("ssmID", etUsername2.getText().toString());
                        params.put("password", etPassword2.getText().toString());
                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });


        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManufacturerActivity.this);

                builder.setCancelable(true);
                builder.setTitle("Manufacturer Register Info");
                builder.setMessage("Manufacturer username must be the company's SSM number");

                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    //register
    private void Register(){

        final String ssmID = this.etUsername2.getText().toString().trim();
        final String password2 = this.etPassword2.getText().toString().trim();
        String URL_REGISTER = "http://192.168.43.131/verifyhut/manufacturer/register.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(ManufacturerActivity.this, "Register Success!", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(ManufacturerActivity.this, MnfHome.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(ManufacturerActivity.this, "Register Unsuccessful!" + e.toString(), Toast.LENGTH_SHORT);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ManufacturerActivity.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT);

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ssmID", ssmID);
                params.put("password", password2);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ManufacturerActivity.this);
        requestQueue.add(stringRequest);
    }
}
