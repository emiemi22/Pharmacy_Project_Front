package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pharmacy.network.JsonPlaceHolderApi;
import com.example.pharmacy.network.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UnknownFormatConversionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductViewActivity extends AppCompatActivity {
    Button redirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        redirect = findViewById(R.id.menuRedirectViewBtn);

        ListView listView = findViewById(R.id.listViewV);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Object>> call = jsonPlaceHolderApi.getProducts();
        Log.d("ProductViewActivity", "Al meu msj " + call.toString());

        call.enqueue( new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Code " + response.code());
                    Log.d("ProductViewActivity", "No Resp " + call.toString());
                    return;
                }
                Log.d("ProductViewActivity", "Resp " + call.toString());
                List<Object> products = response.body();
                List<String> productsView = new ArrayList<>();
                for (Object p : products) {
                    //throw new UnknownFormatConversionException(p.toString());
//                    Product product = (Product) p;
                    String str = p.toString();
                    productsView.add(str);
                    Log.d("ProductViewActivity", "Objects " + p.toString() + " type " + p.getClass())
                    ;
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, productsView);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                Toast.makeText(ProductViewActivity.this, "Error happened 404", Toast.LENGTH_SHORT).show();
                Log.d("ProductViewActivity", "Fail  " + call.toString());
                t.printStackTrace();
            }
        });


        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductViewActivity.this, MenuEmployeeActivity.class));
            }
        });



    }
}