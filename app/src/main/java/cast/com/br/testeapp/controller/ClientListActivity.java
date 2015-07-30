package cast.com.br.testeapp.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import org.apache.http.protocol.HTTP;

import java.util.List;

import cast.com.br.testeapp.model.entity.Client;
import cast.com.br.testeapp.R;
import cast.com.br.testeapp.model.persistence.MemoryClientRepository;

public class ClientListActivity extends AppCompatActivity {

    private ListView listViewClients;
    private Client client;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        bindButton();
        bindClientList();
    }

    private void bindButton() {
        btnAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCrudActivity = new Intent(ClientListActivity.this, ClientCrudActivity.class);
                startActivity(goToCrudActivity);
            }
        });
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
        this.listViewClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client client = (Client) parent.getItemAtPosition(position);
                // Best Practices: http://stackoverflow.com/questions/4275678/how-to-make-phone-call-using-intent-in-android
                final Intent goToSOPhoneCall = new Intent(Intent.ACTION_DIAL /* or Intent.ACTION_DIAL (no manifest permission needed) */);
                goToSOPhoneCall.setData(Uri.parse("tel:" + client.getPhone()));
                startActivity(goToSOPhoneCall);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(ClientListActivity.this);
                builder.setMessage(R.string.confirm_delete).setTitle(R.string.delete).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        client.delete();
                        Toast.makeText(ClientListActivity.this, R.string.deleted_message, Toast.LENGTH_LONG).show();
                        refreshClientList();
                    }
                }).create().show();
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
                // Create the text message with a string
                final Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.enjoy));
                sendIntent.setType(HTTP.PLAIN_TEXT_TYPE);

                // Create intent to show the chooser dialog
                final Intent chooser = Intent.createChooser(sendIntent, getString(R.string.share));

                // Verify the original intent will resolve to at least one activity
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
