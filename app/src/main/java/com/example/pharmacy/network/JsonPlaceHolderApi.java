package com.example.pharmacy.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("product")
    Call<List<Product>> getProducts();

    @FormUrlEncoded
    @POST("product")
    Call<User> addProduct(@Field("productName") String productName,
                       @Field("price") double price,
                       @Field("categoryType") int categoryType,
                       @Field("stock") int stock ) ;

    @DELETE("product/{id}")
    Call<User> deleteProduct(@Path("id") long id);
}
