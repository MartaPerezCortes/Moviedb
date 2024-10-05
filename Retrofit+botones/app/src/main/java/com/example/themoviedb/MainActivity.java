package com.example.themoviedb;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.themoviedb.json_mapper.Movie;
import com.example.themoviedb.json_mapper.MovieResponse;
import com.example.themoviedb.listeners.ListenerFightClub;
import com.example.themoviedb.retrofit.RetrofitClient;
import com.example.themoviedb.services.ServicePantallaPrincipal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnPopulares;
    private Button btnTitanic;
    private Button btnFightClub;
    private static MainActivity padre;
    private Context context;

    public static MainActivity getPadre(){ //devuelve su referencia para poder llamarlo
        return padre;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context=this;
        this.padre = this;


        ServicePantallaPrincipal service=new ServicePantallaPrincipal(this);
        btnPopulares = findViewById(R.id.btnPopulares);
        btnTitanic = findViewById(R.id.btnTitanic);
        btnFightClub = findViewById(R.id.btnDetalles);


        btnPopulares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Llama a la función para obtener películas populares
                service.getPopulares();
            }
        });

        btnTitanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.searchMovies("Titanic", 1);
            }
        });

        btnFightClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea la implementación del listener para manejar el evento
                ListenerFightClub listenerFightClub = new ListenerFightClub() {
                    @Override
                    public void onFightClub() {
                        // Lo que quieras hacer cuando se encuentre "Fight Club"
                        Toast.makeText(context, "¡Fight Club ha sido encontrado!", Toast.LENGTH_SHORT).show();
                    }
                };

                // Llama al servicio pasándole el listener
                service.getMovieDetails(550,listenerFightClub);
            }
        });


    }
}