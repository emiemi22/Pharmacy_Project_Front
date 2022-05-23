package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pharmacy.network.JsonPlaceHolderApi;
import com.example.pharmacy.network.NullOnEmptyConverterFactory;
import com.example.pharmacy.network.Product;
import com.example.pharmacy.network.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDeleteActivity extends AppCompatActivity {
    Button delete, redirect;
    static long idSelectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_delete);
        delete = findViewById(R.id.deleleDeleteProductBtn);
        redirect = findViewById(R.id.menuRedirectDeleteBtn);
        ListView listView = findViewById(R.id.listViewDelete);
        List<Product> list = new ArrayList<>();
        //GET THE PRODUCTS

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Product>> call = jsonPlaceHolderApi.getProducts();
        Log.d("ProductViewActivity", "Al meu msj " + call.toString());

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Code " + response.code());
                    Log.d("ProductDeleteActivity", "No Resp " + call.toString());
                    return;
                }
                Log.d("ProductDeleteActivity", "Resp " + call.toString());
                List<Product> products = response.body();
                if (products == null) {
                    Log.d("NULL", "No products in DB!");
                } else {
                    list.addAll(products);
                }

                ArrayAdapter<Product> arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductDeleteActivity.this, "Error happened 404", Toast.LENGTH_SHORT).show();
                Log.d("ProductDeleteActivity", "Fail  " + call.toString());
                t.printStackTrace();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product selectedProduct = (Product) listView.getItemAtPosition(i);
                idSelectedProduct = selectedProduct.getId();
                Log.d("Selected", selectedProduct.toString());
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/api/v1/")
                        .addConverterFactory(new NullOnEmptyConverterFactory())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                Call<Product> call = jsonPlaceHolderApi.deleteProduct(idSelectedProduct);

                call.enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        Log.d("Activity DELETE", "ONRESPONSE" + String.valueOf(response.body()));
                        Toast.makeText(ProductDeleteActivity.this, "Product deleted!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Log.d("Activity DELETE", "ONFAILUIRE" + String.valueOf(call));
                        t.printStackTrace();
                    }
                });
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