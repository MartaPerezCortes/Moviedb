package com.example.themoviedb.services;

import android.content.Context;
import android.widget.Toast;

import com.example.themoviedb.json_mapper.Movie;
import com.example.themoviedb.json_mapper.MovieResponse;
import com.example.themoviedb.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicePantallaPrincipal {

    private Context context;
    private static final String API_KEY = "4e21b129094e725e7136e5341af06f19"; // Cambia esto por tu clave de API

    public ServicePantallaPrincipal(Context context) {
        this.context = context;
    }

    // Obtener películas populares
    // ServicePantallaPrincipal.java
    public void getPopulares() {
        Call<MovieResponse> call = RetrofitClient.getInstance().getPopularMovies(API_KEY, "es-ES", 1);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getResults();
                    for (Movie myMovie : movies) {
                        // Aquí podrías crear un método para mostrar las películas en una lista o RecyclerView
                        Toast.makeText(context, "Movie: " + myMovie.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Fallo en la respuesta: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Buscar películas
    public void searchMovies(String query, int page) {
        Call<MovieResponse> call = RetrofitClient.getInstance().searchMovies(API_KEY, "es-ES", query, page);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getResults();
                    for (Movie myMovie : movies) {
                        Toast.makeText(context, "Found: " + myMovie.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Fallo en la respuesta: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Obtener detalles de una película
    public void getMovieDetails(int movieId) {
        Call<Movie> call = RetrofitClient.getInstance().getMovieDetails(movieId, API_KEY, "es-ES");

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    Movie movie = response.body();
                    Toast.makeText(context, "Details: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Fallo en la respuesta: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
