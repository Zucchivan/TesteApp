package cast.com.br.testeapp.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cast.com.br.testeapp.model.entity.Client;
import cast.com.br.testeapp.model.entity.ClientUser;

/**
 * Created by Administrador on 30/07/2015.
 */
public class ClientUserContract {

    public static final String TABLE  = "client_user";
    public static final String ID  = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD= "admin";


    public static final String[] COLUMNS = {ID, USERNAME, PASSWORD};

    public static ContentValues getContentValues(ClientUser user){
        ContentValues values = new ContentValues();

        values.put(ClientUserContract.ID, user.getId());
        values.put(ClientUserContract.USERNAME, user.getUsername());
        values.put(ClientUserContract.PASSWORD, user.getPassword());

        return values;
    }

    public static ClientUser bind(Cursor cursor){
        if(!cursor.isBeforeFirst() || cursor.moveToNext()){
            ClientUser user = new ClientUser();
            user.setId(cursor.getInt(cursor.getColumnIndex(ClientUserContract.ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(ClientUserContract.USERNAME)));
            return user;
        }
        return null;
    }

    public static List<ClientUser> bindList(Cursor cursor){
        List<ClientUser> users = new ArrayList<>();
        while(cursor.moveToNext()){
            ClientUser user = bind(cursor);
            users.add(user);
        }
        return users;
    }

    public static String getCreateTable(){
        StringBuilder sb = new StringBuilder();

        sb.append(" CREATE TABLE ")
                .append(TABLE)
                .append(" ( ")
                .append(ID).append(" INTEGER PRIMARY KEY, ")
                .append(USERNAME).append(" TEXT, ")
                .append(PASSWORD).append(" TEXT ")
                .append(" ); ");
        return sb.toString();
    }

    public static String getInsertAdminUser(){
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO ")
                .append(TABLE)
                .append(" ( ")
                .append(USERNAME)
                .append(", " + PASSWORD)
                .append(" ) ")
                .append(" VALUES ")
                .append(" ( ")
                .append(" '"+ ADMIN_USERNAME + "', ")
                .append(" '"+ ADMIN_PASSWORD + "' ")
                .append(" ); ");
        return sb.toString();
    }
}
