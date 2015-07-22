package cast.com.br.testeapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

import cast.com.br.testeapp.model.entity.Client;
import cast.com.br.testeapp.R;
import cast.com.br.testeapp.model.persistence.MemoryClientRepository;

public class TesteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        List<Client> clients = getClients();

        ClientListAdapter adapter = new ClientListAdapter(TesteActivity.this, MemoryClientRepository.getInstance().getAll());

        ListView listViewClients = (ListView) findViewById(R.id.listViewClients);

        listViewClients.setAdapter(adapter);
    }

    private List<Client> getClients() {
        return MemoryClientRepository.getInstance().getAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_client_listview, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemAddClient:
                Intent goToMainActivity = new Intent(TesteActivity.this, ClientCrudActivity.class);
                startActivity(goToMainActivity);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
