package com.example.pharmacy.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonUserApi {
    @GET("user_table")
    Call<List<User>> getUsersList();
}
