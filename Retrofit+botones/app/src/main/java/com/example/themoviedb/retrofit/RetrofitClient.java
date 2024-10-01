package com.example.themoviedb.retrofit;

import com.example.themoviedb.movies_api.MoviesAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static MoviesAPI instance;

    public static MoviesAPI getInstance() { //SINGLETON
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder() //sirve para concatenar metodos estaticos que luego puedo personalizar
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            instance = retrofit.create(MoviesAPI.class);
        }

        return instance;

    }
}