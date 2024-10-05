package com.example.themoviedb.retrofit;

import com.example.themoviedb.movies_api.MoviesAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static MoviesAPI instance;

    public static MoviesAPI getInstance() { //SINGLETON. Este método es estático, lo que significa que se puede llamar sin crear una instancia de RetrofitClient.
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder() //sirve para concatenar metodos estaticos que luego puedo personalizar
                                                        //se crea un objeto de construcción (builder) que permite personalizar y configurar varias propiedades de la instancia de Retrofit que se desea construir.
                    .baseUrl(BASE_URL) //Configura la URL base de la API para Retrofit, usando la constante BASE_URL definida anteriormente.
                    .addConverterFactory(GsonConverterFactory.create()) //se encarga de convertir las respuestas JSON de la API en objetos Java.
                    .build(); // Construye el objeto Retrofit con las configuraciones anteriores.

            instance = retrofit.create(MoviesAPI.class); //crear una instancia de la interfaz MoviesAPI.
                                                        //pasamos el objeto Class que representa la interfaz MoviesAPI a Retrofit.
        }

        return instance;

    }
}