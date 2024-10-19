package com.enrique.rakutentv.login_user;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.enrique.rakutentv.beans.User;
import com.enrique.rakutentv.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginUserModel implements LoginUserContract.Model {

    private OnLoginUserListener onLoginUserListener;
    private ArrayList<User> lstUsers;

    @Override
    public void getUserService(final OnLoginUserListener onLoginUserListener, User usuario) {
        this.onLoginUserListener = onLoginUserListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "USUARIO.LOGIN");
        param.put("EMAIL", usuario.getEmail());
        param.put("PASSWORD", usuario.getPassword());

        // Cambiar URL según IP
        String url = "http://192.168.1.137:8080/RakutenTVAndroid/Controller";
        executeTask(param, url);
    }

    private void executeTask(HashMap<String, String> param, String url) {
        // Crear un ExecutorService con un solo hilo
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                Post post = new Post();
                JSONArray result = post.getServerDataPost(param, url);
                lstUsers = User.getArrayListFromJSon(result);

                // Ejecutar en el hilo principal después de obtener los datos
                handler.post(() -> {
                    if (lstUsers != null && !lstUsers.isEmpty()) {
                        onLoginUserListener.onFinished(lstUsers.get(0));
                    } else {
                        onLoginUserListener.onFailure("Fallo: Login Users - Lista vacía o nula");
                    }
                });
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
                handler.post(() -> onLoginUserListener.onFailure("Fallo: Error en la conexión"));
            }
        });
    }
}
