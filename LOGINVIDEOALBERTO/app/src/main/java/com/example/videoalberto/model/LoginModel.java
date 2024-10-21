package com.example.videoalberto.model;

import android.util.Log;

import com.example.videoalberto.ContractLogin;
import com.example.videoalberto.beans.Usuarios;
import com.example.videoalberto.presenter.LoginPresenter;
import com.example.videoalberto.utils.ApiService;
import com.example.videoalberto.utils.LoginParams;
import com.example.videoalberto.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements ContractLogin.Model {

    private static final String IP_BASE="10.0.2.2:3000";
    private LoginPresenter prsenter;

    public LoginModel(LoginPresenter prsenter) {
        this.prsenter = prsenter;
    }

    @Override
    public void loginAPI(Usuarios usuario, LoginListener onLoginListener) {
        ApiService apiService= RetrofitClient.getClient("http://"+IP_BASE+"").create(ApiService.class);

        LoginParams loginParams=new LoginParams(usuario.getEmail(),usuario.getPassword());

        Call<Usuarios> call=apiService.login(loginParams.getEmail(), loginParams.getPassword());

        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                if(response.isSuccessful()){
                    Usuarios usuarioLogin=response.body();

                    if (usuarioLogin!=null){
                        onLoginListener.onFinished(usuarioLogin);
                    }else {
                        onLoginListener.onFailure("No se encontro el usuario");
                    }
                }
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                Log.e("Error de respuesta", "Error"+t.getMessage());
            }
        });

    }
}
