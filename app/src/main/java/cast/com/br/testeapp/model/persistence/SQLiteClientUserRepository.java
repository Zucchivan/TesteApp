package cast.com.br.testeapp.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import cast.com.br.testeapp.model.entity.Client;
import cast.com.br.testeapp.model.entity.ClientUser;
import cast.com.br.testeapp.util.AppUtil;

/**
 * Created by Administrador on 30/07/2015.
 */
public class SQLiteClientUserRepository implements ClientUserRepository {
    private static SQLiteClientUserRepository singletonInstance;

    private SQLiteClientUserRepository(){
        super();
    }

    public static SQLiteClientUserRepository getInstance(){
        if(SQLiteClientUserRepository.singletonInstance == null){
            SQLiteClientUserRepository.singletonInstance = new SQLiteClientUserRepository();
        }
        return SQLiteClientUserRepository.singletonInstance;
    }

    @Override
    public void save(ClientUser user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = ClientUserContract.getContentValues(user);

        if(user.getId() == null){
            db.insert(ClientUserContract.TABLE, null, values);
        } else {
            String where = " ID = " + user.getId();
            db.update(ClientUserContract.TABLE, values, where, null);
        }
        db.close();
        helper.close();
    }

    @Override
    public List<ClientUser> getAll() {

        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(ClientUserContract.TABLE, ClientUserContract.COLUMNS, null, null, null, null, ClientUserContract.ID);

        List<ClientUser> users = ClientUserContract.bindList(cursor);

        db.close();
        helper.close();

        return users;
    }

    @Override
    public void delete(ClientUser user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = " ID = " + user.getId();

        db.delete(ClientContract.TABLE, where, null);

        db.close();
        helper.close();
    }

    @Override
    public boolean isAuthenticated(String username, String password) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            return db.query(ClientUserContract.TABLE,
                    ClientUserContract.COLUMNS,
                    ClientUserContract.USERNAME + "=?" + " AND " + ClientUserContract.PASSWORD + "=?",
                    new String[]{username, password},
                    null, null, null).moveToFirst();
        } finally {
            db.close();
        }
    }
}
