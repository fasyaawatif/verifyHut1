package com.example.verifyhut;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UpdateManufacturer extends AppCompatActivity {

    String HttpURL = "http://192.168.43.131/verifyhut/product/updateManufacturer.php";
    String url = "http://192.168.43.131/verifyhut/product/upload.php";

    ProgressDialog progressDialog;
    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    final int CODE_GALERY_REQUEST = 999;
    Bitmap bitmap;


    EditText mSsmID, mEmail, mName, mDesc, mPhone, mAddress;
    ImageView mProfile, mBtnMnfChoose;
    String ssmIDHolder, mnfEmailHolder, mnfNameHolder, mnfDescHolder, mnfPhoneHolder, mnfAddressHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_manufacturer);

        mProfile = (ImageView)findViewById(R.id.mnfImg);
        mBtnMnfChoose = (ImageView)findViewById(R.id.btnImgChoose);
        mSsmID = (EditText)findViewById(R.id.mnfSsm);
        //mPassword = (EditText)findViewById(R.id.mnfPassword);
        mEmail = (EditText)findViewById(R.id.mnfEmail);
        mName = (EditText)findViewById(R.id.mnfName);
        mDesc = (EditText)findViewById((R.id.mnfDesc));
        mPhone = (EditText)findViewById(R.id.mnfPhone);
        mAddress = (EditText)findViewById(R.id.mnfAddress);


        ssmIDHolder = getIntent().getStringExtra("ssmID");
        //mnfPassHolder = getIntent().getStringExtra("password");
        mnfEmailHolder = getIntent().getStringExtra("email");
        mnfNameHolder= getIntent().getStringExtra("name");
        mnfDescHolder = getIntent().getStringExtra("description");
        mnfPhoneHolder = getIntent().getStringExtra("phone");
        mnfAddressHolder = getIntent().getStringExtra("address");


        mSsmID.setText(ssmIDHolder);
        //mPassword.setText(mnfPassHolder);
        mEmail.setText(mnfEmailHolder);
        mName.setText(mnfNameHolder);
        mDesc.setText(mnfDescHolder);
        mPhone.setText(mnfPhoneHolder);
        mAddress.setText(mnfAddressHolder);


        mBtnMnfChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        UpdateManufacturer.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALERY_REQUEST
                );
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == CODE_GALERY_REQUEST){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), CODE_GALERY_REQUEST);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access gallery", Toast.LENGTH_LONG).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CODE_GALERY_REQUEST && resultCode == RESULT_OK && data != null){
            Uri filePath = data.getData();

            try{
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                mProfile.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }


    public void GetDataFromEditText(){

        ssmIDHolder = mSsmID.getText().toString();
        //mnfPassHolder = mPassword.getText().toString();
        mnfEmailHolder = mEmail.getText().toString();
        mnfNameHolder = mName.getText().toString();
        mnfDescHolder = mDesc.getText().toString();
        mnfPhoneHolder = mPhone.getText().toString();
        mnfAddressHolder = mAddress.getText().toString();
    }

    public void MnfRecordUpdate(final String ssmID, final String email, final String name, final String description, final String phone, final String address){
        class MnfRecordUpdate extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                progressDialog = ProgressDialog.show(UpdateManufacturer.this,"Loading Data", null,true,true);
            }
            @Override
            protected void onPostExecute (String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                Toast.makeText(UpdateManufacturer.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {
                hashMap.put("ssmID",params[0]);
                //hashMap.put("password",params[1]);
                hashMap.put("email",params[1]);
                hashMap.put("name",params[2]);
                hashMap.put("description",params[3]);
                hashMap.put("phone",params[4]);
                hashMap.put("address",params[5]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
    }

        MnfRecordUpdate mnfRecordUpdate = new MnfRecordUpdate();
        mnfRecordUpdate.execute(ssmID,email,name,description,phone,address);
    }

    public boolean onCreateOptionsMenu(Menu add){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_mnf, add);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.addMnfMenu){
            GetDataFromEditText();
            MnfRecordUpdate(ssmIDHolder, mnfEmailHolder, mnfNameHolder, mnfDescHolder, mnfPhoneHolder, mnfAddressHolder);

            progressDialog = new ProgressDialog(UpdateManufacturer.this);
            progressDialog.setTitle("Uploading");
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "error: " + error.toString(), Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    String imageData = imageToString(bitmap);
                    params.put("ssmID", ssmIDHolder);
                    params.put("image", imageData);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(UpdateManufacturer.this);
            requestQueue.add(stringRequest);
            Intent intent = new Intent(UpdateManufacturer.this, MnfHome.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
