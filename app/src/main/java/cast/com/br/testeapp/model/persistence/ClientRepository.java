package cast.com.br.testeapp.model.persistence;

import java.util.List;

import cast.com.br.testeapp.model.entity.Client;

/**
 * Created by Administrador on 21/07/2015.
 */
public interface ClientRepository {

    public void save(Client client);
    public List<Client> getAll();
    public void delete(Client client);

}
