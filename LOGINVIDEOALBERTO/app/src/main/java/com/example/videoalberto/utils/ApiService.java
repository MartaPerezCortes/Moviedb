package com.example.videoalberto.utils;

import com.example.videoalberto.beans.Usuarios;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @GET("/login")
    Call<Usuarios> login(
      @Query("EMAIL") String email,
      @Query("PASSWORD") String password
    );
}
