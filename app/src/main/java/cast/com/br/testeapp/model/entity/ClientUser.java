package cast.com.br.testeapp.model.entity;

import java.util.List;

import cast.com.br.testeapp.model.persistence.SQLiteClientUserRepository;

/**
 * Created by Administrador on 30/07/2015.
 */
public class ClientUser {

    private Integer id;
    private String username;
    private String password;

    public void save(){
        SQLiteClientUserRepository.getInstance().save(this);
    }

    public static List<ClientUser> getAll(){
        return SQLiteClientUserRepository.getInstance().getAll();
    }

    public void delete() {
        SQLiteClientUserRepository.getInstance().delete(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
