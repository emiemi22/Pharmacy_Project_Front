package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuEmployeeActivity extends AppCompatActivity {

    Button add, delete, update, viewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_employee);
        add = findViewById(R.id.addProductMenuEmplBtn);
        delete = findViewById(R.id.deleteProductMenuEmplBtn);
        update = findViewById(R.id.updateProductMenuEmplBtn);
        viewAll = findViewById(R.id.viewProductMenuEmplBtn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuEmployeeActivity.this, ProductAddActivity.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuEmployeeActivity.this, ProductDeleteActivity.class));
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuEmployeeActivity.this, ProductUpdateActivity.class));
            }
        });


        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuEmployeeActivity.this, ProductViewActivity.class));
            }
        });


    }
}