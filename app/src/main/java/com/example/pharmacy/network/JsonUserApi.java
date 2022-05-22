package com.example.pharmacy.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonUserApi {
    @GET("user_table")
    Call<List<User>> getUsersList();

    @FormUrlEncoded
    @POST("user_table")
    Call<User> addUser(@Field("firstName") String firstName,
                       @Field("lastName") String lastName,
                       @Field("emailAddress") String emailAddress,
                       @Field("password") String password,
                       @Field("role") String role);
}
