package com.example.loginandroid_29_09_2023.login_user.model;

import android.util.Log;

import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.login_user.ContractLoginUser;
import com.example.loginandroid_29_09_2023.login_user.model.data.MyData;
import com.example.loginandroid_29_09_2023.login_user.presenter.LoginUserPresenter;
import com.example.loginandroid_29_09_2023.utils.ApiResponse;
import com.example.loginandroid_29_09_2023.utils.ApiService;
import com.example.loginandroid_29_09_2023.utils.LoginParams;
import com.example.loginandroid_29_09_2023.utils.RetrofitCliente;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserModel implements ContractLoginUser.Model {

    private static final String IP_BASE = "10.0.2.2:4000";
    //private static final String IP_BASE = "127.0.0.1:3000";
    private LoginUserPresenter presenter;
    public LoginUserModel(LoginUserPresenter presenter){
        this.presenter = presenter;
    }


    @Override
    public void loginAPI(User user, final OnLoginUserListener onLoginUserListener) {
        // Crear una instancia de ApiService
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "").
                create(ApiService.class);

        // Realizar la solicitud al Servlet
        // Call<MyData> call = apiService.getMyData("1");
        LoginParams loginParams = new LoginParams(user.getEmail(), user.getPassword());
                /*
                {
                      "email": "prueba@example.com",
                      "password": "password1"
                    }
                * */
        Log.d("LoginUserModel", "Intentando conectar al servidor con IP: " + IP_BASE);

        Call<User> call = apiService.login(loginParams.getEmail(), loginParams.getPassword());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Procesar la respuesta aquí
                    User usuarioLogin= response.body();

                    //String message = myData.getMessage();

                    if (usuarioLogin != null) {
                        onLoginUserListener.onFinished(usuarioLogin);
                    } else {
                        onLoginUserListener.onFailure("No se encontraron usuarios.");
                    }

                } else {
                    // Manejar una respuesta no exitosa

                    Log.e("Response Error", "Código de estado HTTP: " + response.code());
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("Response Error", "Cuerpo de error: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Response Error", "Cuerpo de error: " + t.getMessage());
            }


        });
    }
    }

