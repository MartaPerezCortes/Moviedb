package com.example.videoalberto;

import com.example.videoalberto.beans.Usuarios;

public interface ContractLogin {

    public  interface View{
        public void successLogin(Usuarios usuario);
        void failureLogin(String err);
    }
    public interface PResenter{
        void login(Usuarios usuario);
    }
    public interface Model{
        interface  LoginListener{
            void onFinished(Usuarios usuario);
            void onFailure(String err);
        }
        void loginAPI(Usuarios usuario, LoginListener onLoginListener);
    }
}
