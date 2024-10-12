package com.example.disenyoporcentajes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // elementos de pantalla
    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private Button btnE;
    private Button btnF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //

        setContentView(R.layout.activity_main);

        inicializarComponentes();

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pulsado boton A", Toast.LENGTH_SHORT).show();

            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pulsado boton B", Toast.LENGTH_SHORT).show();

            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pulsado boton C", Toast.LENGTH_SHORT).show();

            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pulsado boton D", Toast.LENGTH_SHORT).show();

            }
        });
        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pulsado boton E", Toast.LENGTH_SHORT).show();

            }
        });
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pulsado boton F", Toast.LENGTH_SHORT).show();

            }
        });

    }

    //incializamos componentes

    private void inicializarComponentes(){
        btnA=(Button)findViewById(R.id.btnA); //cast, pero no hace falta porque lo hace automaticamente
        btnB=findViewById(R.id.btnB);
        btnC=findViewById(R.id.btnC);
        btnD=findViewById(R.id.btnD);
        btnE=findViewById(R.id.btnE);
        btnF=findViewById(R.id.btnF);

    }
}