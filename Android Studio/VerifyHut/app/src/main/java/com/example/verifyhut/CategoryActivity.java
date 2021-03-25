package com.example.verifyhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CategoryActivity extends AppCompatActivity {

    CardView btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        btnA = (CardView)findViewById(R.id.btnA);
        btnB = (CardView)findViewById(R.id.btnB);
        btnC = (CardView)findViewById(R.id.btnC);
        btnD = (CardView)findViewById(R.id.btnD);
        btnE = (CardView)findViewById(R.id.btnE);
        btnF = (CardView)findViewById(R.id.btnF);
        btnG = (CardView)findViewById(R.id.btnG);
        btnH = (CardView)findViewById(R.id.btnH);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryA.class);
                startActivity(intent);

            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryB.class);
                startActivity(intent);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryC.class);
                startActivity(intent);
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryD.class);
                startActivity(intent);
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryE.class);
                startActivity(intent);
            }
        });

        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryF.class);
                startActivity(intent);
            }
        });

        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryG.class);
                startActivity(intent);
            }
        });

        btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryH.class);
                startActivity(intent);
            }
        });
    }
}
