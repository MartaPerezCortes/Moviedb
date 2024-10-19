package com.enrique.rakutentv.login_user;

import com.enrique.rakutentv.beans.User;

public interface LoginUserContract {

    public interface View { //METODO DE RESPUESTA
        void sucessLogin(User lstUsers);
        void failureLogin(String message);

    }
    public interface Presenter {
        void getUser(User usuario);
    }
    public interface Model {
        /*Programación Reactiva*/
        interface OnLoginUserListener {
            void onFinished(User usuario);

            void onFailure(String error);
        }
        void getUserService(OnLoginUserListener onLoginUserListener, User usuario); //LE PASAMOS LA INTERFAZ y el usuario cogido de pantalla

    }
}
