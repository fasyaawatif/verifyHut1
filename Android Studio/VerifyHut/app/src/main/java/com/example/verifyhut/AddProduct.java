package com.example.verifyhut;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;
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
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.WriterException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity {

    String url = "http://192.168.43.131/verifyhut/product/addProduct.php";

    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    final int CODE_GALERY_REQUEST = 999;
    Bitmap bitmap;


    ProgressDialog progressDialog;
    EditText prdID, prdName, prdBrand, prdDesc, prdIngredient, prdHalal;
    Spinner prdType;
    Button btnAddPrd, btnQR;
    ImageView prdQR, prdImg, viewImg;
    RequestQueue requestQueue;
    String prdIDHolder, prdNameHolder, prdBrandHolder, prdDescHolder, prdIngredientHolder, prdTypeHolder, prdHalalHolder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        prdID = (EditText)findViewById(R.id.productID);
        prdName = (EditText)findViewById(R.id.productName);
        prdBrand = (EditText)findViewById(R.id.brand);
        prdDesc = (EditText)findViewById(R.id.productDesc);
        prdIngredient = (EditText)findViewById(R.id.ingredient);
        prdHalal = (EditText)findViewById(R.id.halalNum);
        prdType = (Spinner) findViewById(R.id.type);

        prdQR = (ImageView)findViewById(R.id.productQR);
        prdImg = (ImageView)findViewById(R.id.btnImgPrd);
        viewImg = (ImageView)findViewById(R.id.productImg);

        btnQR = (Button)findViewById(R.id.btnQR);
        btnAddPrd =(Button)findViewById(R.id.btnAddProduct);

        requestQueue = Volley.newRequestQueue(AddProduct.this);
        progressDialog = new ProgressDialog(AddProduct.this);

        prdImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(AddProduct.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALERY_REQUEST
                );
            }
        });

        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = prdID.getText().toString();

                if(data.isEmpty()){
                    prdID.setError("This Field is Required");
                }
                else{
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
                    Bitmap qrBits = qrgEncoder.getBitmap();
                    prdQR.setImageBitmap(qrBits);

                    try {
                        String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
                        boolean save = new QRGSaver().save(savePath, data, qrBits, QRGContents.ImageType.IMAGE_JPEG);
                        String result = save ? "Image Saved" : "Image Not Saved";
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });


        btnAddPrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Please Wait, Product Is Inserting");
                progressDialog.show();

                GetValueFromEditText();

                //QRUploadToServer();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                Toast.makeText(AddProduct.this, response, Toast.LENGTH_LONG).show();
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();

                                Toast.makeText(AddProduct.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {

                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();
                        String imageData = imageToString(bitmap);

                        // Adding All values to Params.
                        params.put("productID", prdIDHolder);
                        params.put("productName", prdNameHolder);
                        params.put("brand", prdBrandHolder);
                        params.put("productDesc", prdDescHolder);
                        params.put("type", prdTypeHolder);
                        params.put("ingredient", prdIngredientHolder);
                        params.put("halalCertified", prdHalalHolder);
                        params.put("barcode", imageData);
                        params.put("productImg", imageData);


                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(AddProduct.this);
                requestQueue.add(stringRequest);
                Intent intent = new Intent(AddProduct.this, MnfHome.class);
                startActivity(intent);
                finish();
            }

        });

    }

    public void GetValueFromEditText() {
        prdIDHolder = prdID.getText().toString().trim();
        prdNameHolder = prdName.getText().toString().trim();
        prdBrandHolder = prdBrand.getText().toString().trim();
        prdDescHolder = prdDesc.getText().toString().trim();
        prdTypeHolder = prdType.getSelectedItem().toString().trim();
        prdHalalHolder = prdHalal.getText().toString().trim();
        prdIngredientHolder = prdIngredient.getText().toString().trim();

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
                viewImg.setImageBitmap(bitmap);
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


}
