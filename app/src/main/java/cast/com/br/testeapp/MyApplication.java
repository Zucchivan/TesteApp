package cast.com.br.testeapp;

import android.app.Application;

import cast.com.br.testeapp.util.AppUtil;

/**
 * Created by Administrador on 23/07/2015.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate(){
        AppUtil.CONTEXT = getApplicationContext();
        super.onCreate();
    }

}
