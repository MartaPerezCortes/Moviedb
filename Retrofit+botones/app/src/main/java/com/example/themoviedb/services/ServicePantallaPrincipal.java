package com.example.themoviedb.services;

import android.content.Context;
import android.widget.Toast;

import com.example.themoviedb.json_mapper.Movie;
import com.example.themoviedb.json_mapper.MovieResponse;
import com.example.themoviedb.listeners.ListenerPopulares;
import com.example.themoviedb.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicePantallaPrincipal {

    private ListenerPopulares misPopulares;
    private Context context; // Añade un contexto

    public ServicePantallaPrincipal(Context context) {
        this.context = context; // Inicializa el contexto
    }

    public void setMyListener(ListenerPopulares misPopulares) {
        this.misPopulares = misPopulares;
    }

    public void getPopulares() {
        Call<MovieResponse> call = RetrofitClient.getInstance().getPopularMovies();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getResults();
                    for (Movie myMovie : movies) {
                        // Utiliza el contexto para mostrar el Toast
                        Toast.makeText(context, "Movie: " + myMovie.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}



