package com.example.verifyhut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ViewCertification extends AppCompatActivity {

    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_certification);

        btnDone = (Button)findViewById(R.id.btnDone1);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCertification.this, MnfHome.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu add){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_cert, add);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.viewUploadSSM){

            Intent intent = new Intent(ViewCertification.this, CertificationActivity.class);
            startActivity(intent);
            finish();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
