package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProductDeleteActivity extends AppCompatActivity {
    Button delete, redirect ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_delete);

        ListView listView = findViewById(R.id.listViewDelete);
        List<String> list = new ArrayList<>();
        list.add("Ibuprofen");
        list.add("Tantum");
        list.add("Aerius");
        list.add("Nurofen");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        delete = findViewById(R.id.deleleDeleteProductBtn);
        redirect = findViewById(R.id.menuRedirectDeleteBtn);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDeleteActivity.this, MenuEmployeeActivity.class));
            }
        });
    }
}