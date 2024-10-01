package com.example.themoviedb.movies_api;

import com.example.themoviedb.json_mapper.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesAPI {
    //ROUTES!! express.js
    @GET("movie/popular?api_key=4e21b129094e725e7136e5341af06f19") //mapemaos URL
    Call<MovieResponse> getPopularMovies(); //lo trasforma en un objeto en java, en este caso MovieResponse

    // Retrofit lo que hace es traducir de java a protocolo rest. Convierte el json en un objeto java (MovieResponse)
    // va a interpretar el objeto json y convertirlo en una array de objetos Movie con sus atributos
}
