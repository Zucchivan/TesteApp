package cast.com.br.testeapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cast.com.br.testeapp.R;
import cast.com.br.testeapp.model.entity.ClientUser;
import cast.com.br.testeapp.model.persistence.SQLiteClientUserRepository;
import cast.com.br.testeapp.util.FormHelper;

/**
 * Created by Administrador on 20/07/2015.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindFields();
    }

  /*  private void bindLoginButton() {
        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToMainActivity = new Intent(LoginActivity.this, ClientListActivity.class);
                startActivity(goToMainActivity);
            }
        });
    }
    */

    private void bindFields(){
        editTextUsername = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyRequiredFields()) {
                    if (verifyUser()) {
                        Intent goToMainActivity = new Intent(LoginActivity.this, ClientListActivity.class);
                        startActivity(goToMainActivity);
                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.invalidUserData), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean verifyRequiredFields() {
        return FormHelper.requireValidate(LoginActivity.this, editTextUsername, editTextPassword);
    }

    private boolean verifyUser() {
        return SQLiteClientUserRepository.getInstance().isAuthenticated(
                editTextUsername.getText().toString(), editTextPassword.getText().toString());
    }
}
