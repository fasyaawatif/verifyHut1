package com.example.verifyhut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MnfHome extends AppCompatActivity {

    Button updateMnf, addPdt, listPdt, viewMnf, logout2, viewSSM;
    String ssmIDHolder, mnfPassHolder, mnfEmailHolder, mnfNameHolder, mnfDescHolder, mnfPhoneHolder, mnfAddressHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnf_home);

        updateMnf = (Button)findViewById(R.id.updateManufacturer);
        addPdt = (Button)findViewById(R.id.addProduct);
        listPdt = (Button)findViewById(R.id.listProduct);
        logout2 = (Button)findViewById(R.id.btnLogout2);
        viewMnf = (Button)findViewById(R.id.btnViewMnf);
        viewSSM = (Button)findViewById(R.id.viewCert);

        addPdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddProduct.class);
                startActivity(intent);
            }
        });

        viewMnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewMnfDetail.class);
                startActivity(intent);
            }
        });


        logout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MnfHome.this, "Logout Successfully", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(MnfHome.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        listPdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewProduct.class);
                startActivity(intent);
            }
        });

        viewSSM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewCertification.class);
                startActivity(intent);
            }
        });

        updateMnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateManufacturer.class);

                intent.putExtra("ssmID", ssmIDHolder);
                intent.putExtra("password", mnfPassHolder);
                intent.putExtra("email", mnfEmailHolder);
                intent.putExtra("name", mnfNameHolder);
                intent.putExtra("description", mnfDescHolder);
                intent.putExtra("phone", mnfPhoneHolder);
                intent.putExtra("address", mnfAddressHolder);
                startActivity(intent);
                //finish();
            }
        });

    }
}
