package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductUpdateActivity extends AppCompatActivity {
    Button update, redirect ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_update);
        update = findViewById(R.id.updateProductBtn);
        redirect = findViewById(R.id.menuRedirectUpdateBtn);
        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductUpdateActivity.this, MenuEmployeeActivity.class));
            }
        });
    }
}