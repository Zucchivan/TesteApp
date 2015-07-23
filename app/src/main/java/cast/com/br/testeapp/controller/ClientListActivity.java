package cast.com.br.testeapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cast.com.br.testeapp.model.entity.Client;
import cast.com.br.testeapp.R;
import cast.com.br.testeapp.model.persistence.MemoryClientRepository;

public class ClientListActivity extends AppCompatActivity {

    private ListView listViewClients;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        bindClientList();
    }

    private void bindClientList() {
        List<Client> clients = getClients();
        final ClientListAdapter adapter = new ClientListAdapter(ClientListActivity.this, MemoryClientRepository.getInstance().getAll());
        this.listViewClients = (ListView) findViewById(R.id.listViewClients);
        this.listViewClients.setAdapter(adapter);
        this.listViewClients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                client = adapter.getItem(position);
                return false;
            }
        });

        registerForContextMenu(listViewClients);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_update_delete_client, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editClient:
                Intent goToCrudActivity = new Intent(ClientListActivity.this, ClientCrudActivity.class);
                goToCrudActivity.putExtra(ClientCrudActivity.CLIENT_PARAM, (Parcelable) client);
                startActivity(goToCrudActivity);
                break;
            case R.id.deleteClient:
                client.delete();
                refreshClientList();
                Toast.makeText(ClientListActivity.this, R.string.deleted_message, Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshClientList();
    }

    private void refreshClientList() {
        ClientListAdapter adapter = (ClientListAdapter) listViewClients.getAdapter();
        adapter.addAll(Client.getAll());
        adapter.notifyDataSetChanged();
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
                Intent goToCrudActivity = new Intent(ClientListActivity.this, ClientCrudActivity.class);
                startActivity(goToCrudActivity);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
