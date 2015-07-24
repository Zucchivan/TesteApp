package cast.com.br.testeapp.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cast.com.br.testeapp.model.entity.Client;

/**
 * Created by Administrador on 23/07/2015.
 */
public class ClientContract {

    public static final String TABLE  = "client";
    public static final String ID  = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";

    public static final String[] COLUMNS = {ID, NAME, AGE, PHONE, ADDRESS};

    public static ContentValues getContentValues(Client client){
        ContentValues values = new ContentValues();

        values.put(ClientContract.ID, client.getId());
        values.put(ClientContract.NAME, client.getName());
        values.put(ClientContract.AGE, client.getAge());
        values.put(ClientContract.PHONE, client.getPhone());
        values.put(ClientContract.ADDRESS, client.getAddress());

        return values;
    }

    public static Client bind(Cursor cursor){
        if(!cursor.isBeforeFirst() || cursor.moveToNext()){
            Client client = new Client();
            client.setName(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            client.setAge(cursor.getInt(cursor.getColumnIndex(ClientContract.AGE)));
            client.setPhone(cursor.getString(cursor.getColumnIndex(ClientContract.PHONE)));
            client.setAddress(cursor.getString(cursor.getColumnIndex(ClientContract.ADDRESS)));
            return client;
        }
        return null;
    }

    public static List<Client> bindList(Cursor cursor){
        List<Client> clients = new ArrayList<>();
        while(cursor.moveToNext()){
            Client client = bind(cursor);
            clients.add(client);
        }
        return clients;
    }

    public static String getCreateTable(){
        StringBuilder sb = new StringBuilder();

        sb.append(" CREATE TABLE ")
        .append(TABLE)
        .append(" ( ")
        .append(ID).append(" INTEGER PRIMARY KEY, ")
        .append(NAME).append(" TEXT, ")
        .append(AGE).append(" INTEGER, ")
        .append(PHONE).append(" TEXT, ")
        .append(ADDRESS).append(" TEXT ")
        .append(" ); ");

        return sb.toString();
    }

}
