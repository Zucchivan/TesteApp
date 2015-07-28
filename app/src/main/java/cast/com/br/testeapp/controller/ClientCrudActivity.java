package cast.com.br.testeapp.controller;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cast.com.br.testeapp.R;
import cast.com.br.testeapp.model.entity.Client;
import cast.com.br.testeapp.model.entity.ClientAddress;
import cast.com.br.testeapp.model.services.CepService;
import cast.com.br.testeapp.util.FormHelper;

/**
 * Created by Administrador on 21/07/2015.
 */
public class ClientCrudActivity extends AppCompatActivity {

    public static String CLIENT_PARAM = "CLIENT_PARAM";

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextPhone;
    private EditText editTextZipCode;
    private EditText editTextStreetType;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private EditText editTextCity;
    private EditText editTextState;
    private Button btnFindAddress;
    private List<EditText> editTexts;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_client);

        Bundle extras = getIntent().getExtras();

        editTextName = (EditText) findViewById(R.id.editTextClientName);
        editTextAge = (EditText)findViewById(R.id.editTextClientAge);
        editTextPhone = (EditText)findViewById(R.id.editTextClientPhone);
        editTextZipCode = (EditText)findViewById(R.id.editTextClientZipCode);
        editTextStreetType = (EditText)findViewById(R.id.editTextClientStreetType);
        editTextStreet = (EditText)findViewById(R.id.editTextClientStreet);
        editTextNeighborhood = (EditText)findViewById(R.id.editTextClientNeighborhood);
        editTextCity = (EditText)findViewById(R.id.editTextClientCity);
        editTextState = (EditText)findViewById(R.id.editTextClientState);
        btnFindAddress = (Button)findViewById(R.id.btnFindAddress);
        btnFindAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetAddressByCep().execute(editTextZipCode.getText().toString());
            }
        });

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
        if(this.client == null) {
            Client cli = new Client();
            cli.setName(editTextName.getText().toString());
            cli.setAge(Integer.valueOf(editTextAge.getText() == null ? "" : editTextAge.getText().toString()));
            cli.setPhone(editTextPhone.getText().toString());
            cli.setZipCode(editTextZipCode.getText().toString());
            cli.setStreetType(editTextStreetType.getText().toString());
            cli.setStreet(editTextStreet.getText().toString());
            cli.setNeighborhood(editTextNeighborhood.getText().toString());
            cli.setCity(editTextCity.getText().toString());
            cli.setState(editTextState.getText().toString());
            return cli;
        } else {
            this.client.setName(editTextName.getText().toString());
            this.client.setAge(Integer.valueOf(editTextAge.getText() == null ? "" : editTextAge.getText().toString()));
            this.client.setPhone(editTextPhone.getText().toString());
            this.client.setZipCode(editTextZipCode.getText().toString());
            this.client.setStreetType(editTextStreetType.getText().toString());
            this.client.setStreet(editTextStreet.getText().toString());
            this.client.setNeighborhood(editTextNeighborhood.getText().toString());
            this.client.setCity(editTextCity.getText().toString());
            this.client.setState(editTextState.getText().toString());
            return this.client;
        }
    }

    private void bindForm(Client clientBind){
        editTextName.setText(clientBind.getName());
        editTextAge.setText(clientBind.getAge().toString());
        editTextPhone.setText(clientBind.getPhone());
        editTextZipCode.setText(clientBind.getZipCode());
        editTextStreetType.setText(clientBind.getStreetType());
        editTextStreet.setText(clientBind.getStreet());
        editTextNeighborhood.setText(clientBind.getNeighborhood());
        editTextCity.setText(clientBind.getCity());
        editTextState.setText(clientBind.getState());
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
        EditText[] array = new EditText[10];

        editTextName = (EditText) findViewById(R.id.editTextClientName);
        editTextAge = (EditText)findViewById(R.id.editTextClientAge);
        editTextPhone = (EditText)findViewById(R.id.editTextClientPhone);
        editTextZipCode = (EditText)findViewById(R.id.editTextClientPhone);
        editTextStreetType = (EditText)findViewById(R.id.editTextClientPhone);
        editTextStreet = (EditText)findViewById(R.id.editTextClientPhone);
        editTextNeighborhood = (EditText)findViewById(R.id.editTextClientPhone);
        editTextCity = (EditText)findViewById(R.id.editTextClientPhone);
        editTextState = (EditText)findViewById(R.id.editTextClientPhone);

        edits.add(editTextName);
        edits.add(editTextAge);
        edits.add(editTextPhone);
        edits.add(editTextZipCode);
        edits.add(editTextStreetType);
        edits.add(editTextStreet);
        edits.add(editTextNeighborhood);
        edits.add(editTextCity);
        edits.add(editTextState);

        int i = 0;
        for(EditText editText : edits){
            array[i] = editText;
            i++;
        }

        return array;
    }

    private class GetAddressByCep extends AsyncTask<String, Void, ClientAddress> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ClientCrudActivity.this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.show();
        }

        @Override
        protected ClientAddress doInBackground(String... params) {
            return CepService.getAddressBy(params[0]);
        }

        @Override
        protected void onPostExecute(ClientAddress clientAddress) {
            progressDialog.dismiss();
            if(clientAddress != null) {
                editTextStreetType.setText(clientAddress.getTipoDeLogradouro());
                editTextStreet.setText(clientAddress.getLogradouro());
                editTextNeighborhood.setText(clientAddress.getBairro());
                editTextCity.setText(clientAddress.getCidade());
                editTextState.setText(clientAddress.getEstado());
            }
        }
    }

}
