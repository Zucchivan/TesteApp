package cast.com.br.testeapp.controller;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cast.com.br.testeapp.model.entity.Client;
import cast.com.br.testeapp.R;

/**
 * Created by Administrador on 20/07/2015.
 */
public class ClientListAdapter extends BaseAdapter {

    private List<Client> clientList;
    private Activity context;

    public ClientListAdapter(Activity context, List<Client> clientList){
        this.context = context;
        this.clientList = clientList;
    }

    @Override
    public int getCount() {
        return this.clientList.size();
    }

    @Override
    public Client getItem(int position) {
        return this.clientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.client_list_item, parent, false);

        Client client = clientList.get(position);

        TextView textName = (TextView) view.findViewById(R.id.textViewName);
        textName.setText(client.getName());

        TextView textAge = (TextView) view.findViewById(R.id.textViewAge);
        textAge.setText(client.getAge().toString());

        TextView textAddress = (TextView) view.findViewById(R.id.textViewAddress);
        textAddress.setText(client.getAddress());

        TextView textPhone = (TextView) view.findViewById(R.id.textViewPhone);
        textPhone.setText(client.getPhone());

        return view;
    }

    public void addAll(List<Client> clients){
        this.clientList = clients;
    }
}
