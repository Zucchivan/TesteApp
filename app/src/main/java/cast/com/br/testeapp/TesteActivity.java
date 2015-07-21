package cast.com.br.testeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class TesteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        ArrayList<Client> clients = getClients();

        ClientListAdapter adapter = new ClientListAdapter(TesteActivity.this, clients);

        ListView listViewClients = (ListView) findViewById(R.id.listViewClients);
        listViewClients.setAdapter(adapter);


    }

    private ArrayList<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<>();
        Client client1 = new Client();
        client1.setAge(19);
        client1.setName("Ivan");

        Client client2 = new Client();
        client1.setAge(439);
        client1.setName("Cleiton");

        clients.add(client1);
        clients.add(client2);
        return clients;
    }
}
