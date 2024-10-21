package com.example.videoalberto.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.videoalberto.ContractLogin;
import com.example.videoalberto.MainActivity;
import com.example.videoalberto.R;
import com.example.videoalberto.beans.Usuarios;
import com.example.videoalberto.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ContractLogin.View {
    private EditText rspEmail;
    private EditText rspPassword;
    private Button btnLogin;

    private LoginPresenter presenter= new LoginPresenter(this);

    private static LoginActivity mainActivity=null;

    public static LoginActivity getInstance(){return mainActivity;}

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mainActivity=this;
        initComponents();

    }

    private void initComponents(){

        rspEmail=findViewById(R.id.rspEmail);
        rspPassword=findViewById(R.id.rspPassword);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=rspEmail.getText().toString();
                String password=rspPassword.getText().toString();

                if(email.isEmpty()||password.isEmpty()){
                    Toast.makeText(mainActivity,"Ingrese sus datos",Toast.LENGTH_SHORT).show();
                    return;
                }

                Usuarios usuario=new Usuarios();
                usuario.setEmail(email);
                usuario.setPassword(password);

                presenter.login(usuario);
            }
        });
    }

    @Override
    public void successLogin(Usuarios usuario) {
        Toast.makeText(mainActivity,"Login exitoso! Bienvenido: "+usuario.getEmail(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureLogin(String err) {
        Toast.makeText(mainActivity,"Error al iniciar sesion "+err, Toast.LENGTH_SHORT).show();
    }
}
