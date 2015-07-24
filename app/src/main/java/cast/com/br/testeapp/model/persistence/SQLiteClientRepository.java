package cast.com.br.testeapp.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cast.com.br.testeapp.model.entity.Client;
import cast.com.br.testeapp.util.AppUtil;

/**
 * Created by Administrador on 23/07/2015.
 */
public class SQLiteClientRepository implements ClientRepository{

    private static SQLiteClientRepository singletonInstance;

    private SQLiteClientRepository(){
        super();
    }

    public static SQLiteClientRepository getInstance(){
        if(SQLiteClientRepository.singletonInstance == null){
            SQLiteClientRepository.singletonInstance = new SQLiteClientRepository();
        }
        return SQLiteClientRepository.singletonInstance;
    }

    @Override
    public void save(Client client) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = ClientContract.getContentValues(client);

        if(client.getId() == null){
            db.insert(ClientContract.TABLE, null, values);
        } else {
            String where = " ID = " + client.getId();
            db.update(ClientContract.TABLE, values, where, null);
        }
        db.close();
        helper.close();
    }

    @Override
    public List<Client> getAll() {

        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(ClientContract.TABLE, ClientContract.COLUMNS, null, null, null, null, ClientContract.NAME);

        List<Client> clients = ClientContract.bindList(cursor);

        db.close();
        helper.close();

        return clients;
    }

    @Override
    public Client getById(Integer id) {

        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(ClientContract.TABLE, ClientContract.COLUMNS, null, null, null, null, ClientContract.NAME);

        Client client = ClientContract.bind(cursor);

        db.close();
        helper.close();

        return client;
    }

    @Override
    public void delete(Client client) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = " ID = " + client.getId();

        db.delete(ClientContract.TABLE, where, null);

        db.close();
        helper.close();
    }
}
