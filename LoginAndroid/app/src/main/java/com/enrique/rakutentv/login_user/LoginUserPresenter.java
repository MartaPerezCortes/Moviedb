package com.enrique.rakutentv.login_user;

import com.enrique.rakutentv.beans.User;

import java.util.ArrayList;

public class LoginUserPresenter implements LoginUserContract.Presenter {
    /*Presenter, tiene que poder hablar con el Modelo
     y desencadenar acciones en la vista*/
    private LoginUserContract.View vista;
    private LoginUserModel modelo;

    public LoginUserPresenter(LoginUserContract.View vista) {
        this.vista = vista;
        this.modelo = new LoginUserModel();
    }

    @Override
    public void getUser(User usuario) {
        /*LLAMAMOS A NUESTOR API QUE ESTA EN EL MODELO*/
        modelo.getUserService(new LoginUserContract.Model.OnLoginUserListener() { // llamamos dentro de la interfaz solo a la parte de model para que aplique
            @Override
            public void onFinished(User usuario) {
                vista.sucessLogin(usuario);
            }

            @Override
            public void onFailure(String error) {
                vista.failureLogin(error);
            }
        }, usuario);
    }
}

