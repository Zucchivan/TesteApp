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
    public static final String ZIPCODE = "zipCode";
    public static final String STREETTYPE = "streetType";
    public static final String STREET = "street";
    public static final String NEIGHBORHOOD = "neighborhood";
    public static final String CITY = "city";
    public static final String STATE = "state";

    public static final String[] COLUMNS = {ID, NAME, AGE, PHONE, ZIPCODE, STREETTYPE, STREET, NEIGHBORHOOD, CITY, STATE};

    public static ContentValues getContentValues(Client client){
        ContentValues values = new ContentValues();

        values.put(ClientContract.ID, client.getId());
        values.put(ClientContract.NAME, client.getName());
        values.put(ClientContract.AGE, client.getAge());
        values.put(ClientContract.PHONE, client.getPhone());
        values.put(ClientContract.ZIPCODE, client.getZipCode());
        values.put(ClientContract.STREETTYPE, client.getStreetType());
        values.put(ClientContract.STREET, client.getStreet());
        values.put(ClientContract.NEIGHBORHOOD, client.getNeighborhood());
        values.put(ClientContract.CITY, client.getCity());
        values.put(ClientContract.STATE, client.getState());

        return values;
    }

    public static Client bind(Cursor cursor){
        if(!cursor.isBeforeFirst() || cursor.moveToNext()){
            Client client = new Client();
            client.setName(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            client.setAge(cursor.getInt(cursor.getColumnIndex(ClientContract.AGE)));
            client.setPhone(cursor.getString(cursor.getColumnIndex(ClientContract.PHONE)));
            client.setZipCode(cursor.getString(cursor.getColumnIndex(ClientContract.ZIPCODE)));
            client.setStreetType(cursor.getString(cursor.getColumnIndex(ClientContract.STREETTYPE)));
            client.setStreet(cursor.getString(cursor.getColumnIndex(ClientContract.STREET)));
            client.setNeighborhood(cursor.getString(cursor.getColumnIndex(ClientContract.NEIGHBORHOOD)));
            client.setCity(cursor.getString(cursor.getColumnIndex(ClientContract.CITY)));
            client.setState(cursor.getString(cursor.getColumnIndex(ClientContract.STATE)));
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
        .append(ZIPCODE).append(" TEXT, ")
        .append(STREETTYPE).append(" TEXT, ")
        .append(STREET).append(" TEXT, ")
        .append(NEIGHBORHOOD).append(" TEXT, ")
        .append(CITY).append(" TEXT, ")
        .append(STATE).append(" TEXT ")
        .append(" ); ");

        return sb.toString();
    }
}
