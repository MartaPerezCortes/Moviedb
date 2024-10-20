package com.example.loginandroid_29_09_2023.login_user.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginandroid_29_09_2023.MainActivity;
import com.example.loginandroid_29_09_2023.R;
import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.login_user.ContractLoginUser;
import com.example.loginandroid_29_09_2023.login_user.presenter.LoginUserPresenter;
import com.example.loginandroid_29_09_2023.lstMov.view.LstMovies;

public class LoginUserM extends AppCompatActivity implements ContractLoginUser.View{

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;

    private LoginUserPresenter presenter =
            new LoginUserPresenter(this);

    /* PATRÓN SINGLETON*/
    private static LoginUserM mainActivity = null;
    public static LoginUserM getInstance(){
        return mainActivity; //0x457845AF
    }
    /* FIN PATRÓN SINGLETON*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_m);
        mainActivity = this;
        initComponents();
    }
    private void initComponents(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el email y el password desde los campos de texto
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                // Validar que los campos no estén vacíos
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(mainActivity, "Por favor, ingrese los datos.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Crear un objeto User con los valores ingresados
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);

                presenter.login(user);
            }
        });
    }

    @Override
    public void successLogin(User user) {
        Toast.makeText(mainActivity, "¡Login exitoso! Bienvenido, " + user.getEmail(), Toast.LENGTH_SHORT).show();

        // Crear un Intent para navegar a LstMoviesActivity
        Intent intent = new Intent(this, LstMovies.class);

        // Si deseas pasar datos adicionales al intent, puedes usar `intent.putExtra()`
        // Por ejemplo:
        // intent.putExtra("USER_EMAIL", user.getEmail());

        // Iniciar la nueva Activity
        startActivity(intent);

        // Opcionalmente, termina la Activity actual si ya no es necesaria
        finish();
    }

    @Override
    public void failureLogin(String err) {
        Toast.makeText(mainActivity, "Error de inicio de sesión: " + err, Toast.LENGTH_SHORT).show();
    }
}