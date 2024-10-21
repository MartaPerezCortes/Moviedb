package com.example.videoalberto.presenter;

import com.example.videoalberto.ContractLogin;
import com.example.videoalberto.beans.Usuarios;
import com.example.videoalberto.model.LoginModel;

public class LoginPresenter implements ContractLogin.PResenter, ContractLogin.Model.LoginListener {

    private ContractLogin.View view;
    private ContractLogin.Model model;

    public LoginPresenter(ContractLogin.View view){
        this.view=view;
        model=new LoginModel(this);
    }
    @Override
    public void login(Usuarios usuario) {
    model.loginAPI(usuario,this);
    }
    @Override
    public void onFinished(Usuarios usuario) {
        view.successLogin(usuario);
    }

    @Override
    public void onFailure(String err) {
        view.failureLogin(err);
    }
}
