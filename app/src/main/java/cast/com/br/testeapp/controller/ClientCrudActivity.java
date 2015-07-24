package cast.com.br.testeapp.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cast.com.br.testeapp.R;
import cast.com.br.testeapp.model.entity.Client;
import cast.com.br.testeapp.util.FormHelper;

/**
 * Created by Administrador on 21/07/2015.
 */
public class ClientCrudActivity extends AppCompatActivity {

    public static String CLIENT_PARAM = "CLIENT_PARAM";

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private List<EditText> editTexts;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_client);

        Bundle extras = getIntent().getExtras();

        editTextName = (EditText) findViewById(R.id.editTextClientName);
        editTextAge = (EditText)findViewById(R.id.editTextClientAge);
        editTextAddress = (EditText)findViewById(R.id.editTextClientAdress);
        editTextPhone = (EditText)findViewById(R.id.editTextClientPhone);

        if(extras != null) {
            this.client = (Client) getIntent().getExtras().getParcelable(CLIENT_PARAM);
            if(client != null){
                bindForm(this.client);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_crud_client, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private Client bindClient(){
        if(client == null) {
            Client client = new Client();
        }
        client.setName(editTextName.getText().toString());
        client.setAge(Integer.valueOf(editTextAge.getText() == null ? "" : editTextAge.getText().toString()));
        client.setAddress(editTextAddress.getText().toString());
        client.setPhone(editTextPhone.getText().toString());
        return client;
    }

    private void bindForm(Client clientBind){
        editTextName.setText(clientBind.getName());
        editTextAge.setText(clientBind.getAge().toString());
        editTextAddress.setText(clientBind.getAddress());
        editTextPhone.setText(clientBind.getPhone());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveItemClientCrud:
                if(FormHelper.requireValidate(ClientCrudActivity.this, this.getArrayEditTexts())){
                    Client client = bindClient();
                    client.save();
                    Toast.makeText(ClientCrudActivity.this, getString(R.string.successSave), Toast.LENGTH_LONG).show();
                    ClientCrudActivity.this.finish();
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private EditText[] getArrayEditTexts(){
        List<EditText> edits = new ArrayList<>();
        EditText[] array = new EditText[4];

        editTextName = (EditText) findViewById(R.id.editTextClientName);
        editTextAge = (EditText)findViewById(R.id.editTextClientAge);
        editTextAddress = (EditText)findViewById(R.id.editTextClientAdress);
        editTextPhone = (EditText)findViewById(R.id.editTextClientPhone);

        edits.add(editTextName);
        edits.add(editTextAge);
        edits.add(editTextAddress);
        edits.add(editTextPhone);

        int i = 0;
        for(EditText editText : edits){
            array[i] = editText;
            i++;
        }

        return array;
    }


}
