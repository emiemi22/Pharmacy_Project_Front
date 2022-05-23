package com.example.pharmacy.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("product")
    Call<List<Product>> getProducts();

    @FormUrlEncoded
    @POST("product")
    Call<Product> addProduct(@Field("productName") String productName,
                       @Field("price") double price,
                       @Field("categoryType") int categoryType,
                       @Field("stock") int stock ) ;

    @DELETE("product/{id}")
    Call<Product> deleteProduct(@Path("id") long id);

    @PUT("product/{id}")
    Call<Product> updateProduct( @Path("id") long id, @Body Product product) ;
}
