package cast.com.br.testeapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cast.com.br.testeapp.R;

/**
 * Created by Administrador on 20/07/2015.
 */
public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindLoginButton();
    }

    private void bindLoginButton() {
        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainActivity = new Intent(LoginActivity.this, ClientListActivity.class);
                startActivity(goToMainActivity);
            }
        });
    }
}
