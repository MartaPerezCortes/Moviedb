package com.example.themoviedb.services;

import android.content.Context;
import android.widget.Toast;

import com.example.themoviedb.json_mapper.Movie;
import com.example.themoviedb.json_mapper.MovieResponse;
import com.example.themoviedb.listeners.ListenerFightClub;
import com.example.themoviedb.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicePantallaPrincipal {

    private Context context; //El contexto de Android, utilizado para mostrar Toast y tener acceso a recursos de la aplicación.
    private static final String API_KEY = "4e21b129094e725e7136e5341af06f19"; // mi api key
    ListenerFightClub listenerFC;
    public ServicePantallaPrincipal(Context context) {
        this.context = context;
    }
    //El constructor de la clase inicializa el contexto de Android, necesario para interactuar con la interfaz de usuario (UI) y mostrar mensajes.

    // Obtener películas populares

    public void getPopulares() {
        Call<MovieResponse> call = RetrofitClient.getInstance().getPopularMovies(API_KEY, "es-ES", 1);
            //Call<MovieResponse> call  objeto que realiza una solicitud HTTP utilizando Retrofit y la API de TheMovieDB.
            // con getInstance: obtener la instancia de la interfaz MoviesAPI que maneja las rutas de la API.
            //El método getPopularMovies() envía una solicitud GET a la API para recuperar las películas más populares.
            // le inciamos los parametros de la solicitud: clave api, idioma y numero de página

        call.enqueue(new Callback<MovieResponse>() {
            // call.enqueue: Realiza la solicitud de manera asíncrona. Esto evita bloquear el hilo principal de la UI.
            // Callback<MovieResponse>(): objeto que define qué debe hacer la aplicación cuando se recibe la respuesta de la API o cuando hay un error.
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) { //Este método se ejecuta cuando la API responde exitosamente.
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body().getResults(); //obtiene la lista de películas del cuerpo de la respuesta
                    for (Movie myMovie : movies) { //Se recorre la lista de películas, y por cada una se muestra un Toast con el título de la película.

                        Toast.makeText(context, "Movie: " + myMovie.getTitle(), Toast.LENGTH_SHORT).show();
                                                                                        //LENGTH_SHORT  indica la duración durante la cual se mostrará un Toast en la pantalla.
                    }
                } else {
                    Toast.makeText(context, "Fallo en la respuesta: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) { // Este método se ejecuta si hay un error en la solicitud HTTP
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
    public void getMovieDetails(int movieId, ListenerFightClub listenerFC) {
        Call<Movie> call = RetrofitClient.getInstance().getMovieDetails(movieId, API_KEY, "en-US");

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()&& response.body() != null) {
                    Movie movie = response.body(); // Aquí obtenemos un solo objeto Movie, no una lista


                    // Verificamos si el título de la película es "Fight Club"
                    if (movie.getTitle()!= null && movie.getTitle().equalsIgnoreCase("Fight Club")) {
                        // Si es "Fight Club", invocamos el listener
                        listenerFC.onFightClub();
                    } else {
                        Toast.makeText(context, "Found: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }else {
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
