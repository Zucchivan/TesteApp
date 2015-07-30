package cast.com.br.testeapp.model.persistence;

import java.util.List;

import cast.com.br.testeapp.model.entity.ClientUser;

/**
 * Created by Administrador on 30/07/2015.
 */
public interface ClientUserRepository {

    public void save(ClientUser user);
    public List<ClientUser> getAll();
    public void delete(ClientUser user);
    public boolean isAuthenticated(String username, String password);

}
