package com.example.verifyhut;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class SuccessActivity extends AppCompatActivity {

    CardView prdList, mnfList, btnLogout, btnScanQR, btnUserProfile, btnCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        prdList = (CardView)findViewById(R.id.prdList);
        mnfList = (CardView)findViewById(R.id.mnfList);
        btnLogout = (CardView)findViewById(R.id.btnLogout);
        btnScanQR = (CardView)findViewById(R.id.btnScanQR);
        btnCategory = (CardView)findViewById(R.id.btnCategory);
        btnUserProfile = (CardView)findViewById(R.id.btnUserProfile);

        final Activity activity = this;

        btnUserProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateConsumer.class);
                startActivity(intent);
            }
        });

        prdList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewProduct1.class);
                startActivity(intent);
            }
        });

        mnfList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewManufacturer.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SuccessActivity.this, "Logout Successfully", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(SuccessActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        btnScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "You cancel the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", result.getContents());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Product Code Copied to Clipboard", Toast.LENGTH_LONG).show();

                String code = result.getContents();
                Intent i = new Intent(SuccessActivity.this, ViewProduct1.class);
                //i.setAction(Intent.ACTION_SEARCH);
                i.putExtra("BARCODE", code);
                startActivity(i);


                //ClipData pasteData = clipboard.getPrimaryClip();
                //ClipData.Item item = pasteData.getItemAt(0);
                //String paste = item.getText().toString();

            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
