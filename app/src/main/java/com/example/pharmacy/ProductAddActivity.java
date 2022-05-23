package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmacy.network.JsonPlaceHolderApi;
import com.example.pharmacy.network.JsonUserApi;
import com.example.pharmacy.network.NullOnEmptyConverterFactory;
import com.example.pharmacy.network.Product;
import com.example.pharmacy.network.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductAddActivity extends AppCompatActivity {
    Button add, redirect;
    EditText productNameTxt, priceTxt, categoryTxt, stockTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        add = findViewById(R.id.addProductBtn);
        redirect = findViewById(R.id.menuRedirectAddBtn);

        productNameTxt = findViewById(R.id.productNameInput);
        priceTxt = findViewById(R.id.PriceInput);
        categoryTxt = findViewById(R.id.categoryTypeInput);
        stockTxt = findViewById(R.id.stockAddInput);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(productNameTxt.getText().toString())
                        || TextUtils.isEmpty(priceTxt.getText().toString())
                        || TextUtils.isEmpty(categoryTxt.getText().toString())
                        || TextUtils.isEmpty(stockTxt.getText().toString())) {
                    Toast.makeText(ProductAddActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    String productName = productNameTxt.getText().toString();
                    double price = Double.parseDouble(priceTxt.getText().toString());
                    int categoryType = Integer.parseInt(categoryTxt.getText().toString());
                    int stock = Integer.parseInt(stockTxt.getText().toString());
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/api/v1/")
                            .addConverterFactory(new NullOnEmptyConverterFactory())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                    Call<Product> call = jsonPlaceHolderApi.addProduct(productName, price, categoryType, stock);

                    call.enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            Log.d("Activity Add", "ONRESPONSE" + String.valueOf(response.body()));
                            Toast.makeText(ProductAddActivity.this, "Product added!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {
                            Log.d("Activity Add", "ONFAILUIRE" + String.valueOf(call));
                            t.printStackTrace();
                        }
                    });
                }


            }
        });
        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductAddActivity.this, MenuEmployeeActivity.class));
            }
        });
    }
}