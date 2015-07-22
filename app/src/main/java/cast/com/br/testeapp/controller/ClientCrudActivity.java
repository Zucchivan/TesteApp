package cast.com.br.testeapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cast.com.br.testeapp.R;
import cast.com.br.testeapp.model.entity.Client;
import cast.com.br.testeapp.model.persistence.ClientRepository;
import cast.com.br.testeapp.model.persistence.MemoryClientRepository;

/**
 * Created by Administrador on 21/07/2015.
 */
public class ClientCrudActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextAddress;
    private EditText editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_client);

        editTextName = (EditText) findViewById(R.id.editTextClientName);
        editTextAge = (EditText)findViewById(R.id.editTextClientAge);
        editTextAddress = (EditText)findViewById(R.id.editTextClientAdress);
        editTextPhone = (EditText)findViewById(R.id.editTextClientPhone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_crud_client, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private Client bindClient(){
        Client client = new Client();
        client.setName(editTextName.getText().toString());
        client.setAge(Integer.valueOf(editTextAge.getText().toString()));
        client.setAddress(editTextAddress.getText().toString());
        client.setPhone(editTextPhone.getText().toString());
        return client;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveItemClientCrud:
                Client client = bindClient();
                client.save();
                Toast.makeText(ClientCrudActivity.this, getString(R.string.successSave), Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
