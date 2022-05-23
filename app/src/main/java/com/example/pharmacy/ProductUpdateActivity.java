package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class ProductUpdateActivity extends AppCompatActivity {
    Button update, redirect;
    EditText productNameTxt, priceTxt, categoryTxt, stockTxt;
    static long idSelectedProduct;
    static Product productSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_update);
        update = findViewById(R.id.updateProductBtn);
        redirect = findViewById(R.id.menuRedirectUpdateBtn);

        productNameTxt = findViewById(R.id.productUpdateNameInput);
        priceTxt = findViewById(R.id.updatePriceInput);
        categoryTxt = findViewById(R.id.categoryUpdateTypeInput);
        stockTxt = findViewById(R.id.productUpdateStockInput);


        ListView listView = findViewById(R.id.listViewUpdate);
        List<Product> list = new ArrayList<>();

        //get the products
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Product>> call = jsonPlaceHolderApi.getProducts();
        Log.d("ProductUpdateActivity", "Al meu msj " + call.toString());

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Code " + response.code());
                    Log.d("ProductUpdateActivity", "No Resp " + call.toString());
                    return;
                }
                Log.d("ProductViewActivity", "Resp " + call.toString());
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
                Toast.makeText(ProductUpdateActivity.this, "Error happened 404", Toast.LENGTH_SHORT).show();
                Log.d("ProductViewActivity", "Fail  " + call.toString());
                t.printStackTrace();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product selectedProduct = (Product) listView.getItemAtPosition(i);
                idSelectedProduct = selectedProduct.getId();
                productSelected = selectedProduct;
                Log.d("Selected", selectedProduct.toString());
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String productName = productNameTxt.getText().toString();
                double price = Double.parseDouble(priceTxt.getText().toString());
                int categoryType = Integer.parseInt(categoryTxt.getText().toString());
                int stock = Integer.parseInt(stockTxt.getText().toString());
                if (TextUtils.isEmpty(productNameTxt.getText().toString())
                        || TextUtils.isEmpty(priceTxt.getText().toString())
                        || TextUtils.isEmpty(categoryTxt.getText().toString())
                        || TextUtils.isEmpty(stockTxt.getText().toString())) {
                    Toast.makeText(ProductUpdateActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {

                    String address = "http://10.0.2.2:8080/api/v1/";

                    Retrofit retrofit = new Retrofit.Builder().baseUrl(address)
                            .addConverterFactory(new NullOnEmptyConverterFactory())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                    // create the logic for update

                    Product updatedProduct = new Product();
                    updatedProduct.setProductName(productName);
                    updatedProduct.setPrice((price));
                    updatedProduct.setId(idSelectedProduct);
                    updatedProduct.setCategoryType(categoryType);
                    updatedProduct.setStock(stock);

                    Log.d("Activity Update", "Input update object " + updatedProduct.toString());

                    Call<Product> call = jsonPlaceHolderApi.updateProduct(idSelectedProduct, updatedProduct);

                    call.enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            Log.d("Activity Update", "ONRESPONSE" + String.valueOf(response.body()));
                            Toast.makeText(ProductUpdateActivity.this, "Product update!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {
                            Log.d("Activity Update", "ONFAILUIRE" + String.valueOf(call));
                            t.printStackTrace();
                        }
                    });
                }

            }
        });

        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductUpdateActivity.this, MenuEmployeeActivity.class));
            }
        });
    }
}