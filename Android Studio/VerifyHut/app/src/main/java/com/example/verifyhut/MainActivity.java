package com.example.verifyhut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    TextView registerBtn, response;
    Button signBtn, manufacturerBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText)findViewById(R.id.userID);
        etPassword = (EditText)findViewById(R.id.password);
        response = (TextView)findViewById(R.id.result);
        registerBtn = (TextView)findViewById(R.id.btnRegister);
        signBtn = (Button)findViewById(R.id.btnSign);
        manufacturerBtn = (Button)findViewById(R.id.btnManufacturer);


        //baru tambah
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "http://192.168.43.131/verifyhut/consumer/api.php";

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
                                            Intent dashboard = new Intent(MainActivity.this, SuccessActivity.class);
                                            dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(dashboard);
                                            finish();
                                            //Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();

                                        }
                                        else if (query_result.equalsIgnoreCase("FAILED")) {
                                            Toast.makeText(MainActivity.this,"Failed Login",Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(MainActivity.this,"Error, parsing data",Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this,"Couldn't access JSON Data",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        response.setText("That didn't work!");
                    }
                }) {
                    //adding parameters to the request
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("method","login");
                        params.put("userID", etUsername.getText().toString());
                        params.put("password", etPassword.getText().toString());
                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
        manufacturerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Manufacturer Page");
                Intent intent = new Intent(getApplicationContext(), ManufacturerActivity.class);
                startActivity(intent);
            }
        });
    }


//baru tambah
    private void Register(){

        final String userID = this.etUsername.getText().toString().trim();
        final String password = this.etPassword.getText().toString().trim();
        String URL_REGISTER = "http://192.168.43.131/verifyhut/consumer/register.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(MainActivity.this, "Register Success!", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Register Unsuccessful!" + e.toString(), Toast.LENGTH_SHORT);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT);

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userID", userID);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

}

