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
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class CertificationActivity extends AppCompatActivity {

    String url = "http://192.168.43.131/verifyhut/product/uploadcert.php";
    ImageView mCertification;
    EditText cSsmID;
    Button btnUploadCert, btnChooseCert;
    ImageView mnfViewCert;
    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    final int CODE_GALERY_REQUEST = 999;
    Bitmap bitmap;
    String ssmIDHolder;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);

        cSsmID = (EditText)findViewById(R.id.insertSSM);
        btnUploadCert = (Button)findViewById(R.id.editCert);
        btnChooseCert = (Button)findViewById(R.id.chooseCert);
        mnfViewCert = (ImageView)findViewById(R.id.mnfSsmCertified);


        ssmIDHolder = getIntent().getStringExtra("ssmID");
        cSsmID.setText(ssmIDHolder);

        btnChooseCert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(CertificationActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALERY_REQUEST
                );
            }
        });

        btnUploadCert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataFromEditText();
                MnfRecordUpdate(ssmIDHolder);
                //progressDialog = new ProgressDialog(CertificationActivity.this);
                //progressDialog.setTitle("Uploading");
                //progressDialog.setMessage("Please wait...");
                //progressDialog.show();
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
                    protected Map<String, String> getParams() throws AuthFailureError{
                        Map<String, String> params = new HashMap<>();
                        String imageData = imageToString(bitmap);
                        params.put("ssmID", ssmIDHolder);
                        params.put("ssmCertified", imageData);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(CertificationActivity.this);
                requestQueue.add(stringRequest);
                Intent intent = new Intent(CertificationActivity.this, ssmCertificate.class);
                startActivity(intent);
                finish();
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
                mnfViewCert.setImageBitmap(bitmap);
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

        ssmIDHolder = cSsmID.getText().toString();

    }

    public void MnfRecordUpdate(final String ssmID){
        class MnfRecordUpdate extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                progressDialog = ProgressDialog.show(CertificationActivity.this,"Loading Data", null,true,true);
            }
            @Override
            protected void onPostExecute (String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                Toast.makeText(CertificationActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {
                hashMap.put("ssmID",params[0]);
                finalResult = httpParse.postRequest(hashMap, url);
                return finalResult;
            }
        }

        MnfRecordUpdate mnfRecordUpdate = new MnfRecordUpdate();
        mnfRecordUpdate.execute(ssmID);
    }
}
