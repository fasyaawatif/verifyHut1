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


public class UpdateConsumer extends AppCompatActivity {


    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    EditText cUserID, cEmail, cName;
    String userIDholder, userEmailholder, userNameholder;

    String url = "http://192.168.43.131/verifyhut/consumer/updateUser.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_consumer);

        cUserID = (EditText)findViewById(R.id.userID);
        cEmail = (EditText)findViewById(R.id.userEmail);
        cName = (EditText)findViewById(R.id.userName);

        requestQueue = Volley.newRequestQueue(UpdateConsumer.this);
        progressDialog = new ProgressDialog(UpdateConsumer.this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu edit){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_user, edit);
        //menuInflater.inflate(R.menu.menu_edit, delete);
        //action1 = edit;
        //action2 = delete;
        //action1.findItem(R.id.editMenu).setVisible(false);
        //action2.findItem(R.id.deleteMenu).setVisible(false);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.addUserMenu){
            //Toast.makeText(UpdatePrdActivity.this, "Product Updated", Toast.LENGTH_SHORT).show();


            progressDialog.setMessage("Please Wait, Product Is Inserting");
            progressDialog.show();

            GetValueFromEditText();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(UpdateConsumer.this, response, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(UpdateConsumer.this, SuccessActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();

                            Toast.makeText(UpdateConsumer.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {

                    // Creating Map String Params.
                    Map<String, String> params = new HashMap<String, String>();

                    // Adding All values to Params.
                    params.put("userID", userIDholder);
                    params.put("email", userEmailholder);
                    params.put("name", userNameholder);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(UpdateConsumer.this);
            requestQueue.add(stringRequest);

        }

        return super.onOptionsItemSelected(item);

    }

    public void GetValueFromEditText() {
        userIDholder = cUserID.getText().toString();
        userEmailholder = cEmail.getText().toString();
        userNameholder = cName.getText().toString();

    }
}
