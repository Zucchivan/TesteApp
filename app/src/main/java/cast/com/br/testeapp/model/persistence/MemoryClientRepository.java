package cast.com.br.testeapp.model.persistence;

import java.util.ArrayList;
import java.util.List;

import cast.com.br.testeapp.model.entity.Client;

/**
 * Created by Administrador on 21/07/2015.
 */
public class MemoryClientRepository implements ClientRepository{

    public static List<Client> clients;

    private static MemoryClientRepository singletonInstance;

    private MemoryClientRepository() {
        super();
        clients = new ArrayList<>();
    }

    public static ClientRepository getInstance(){
        if(singletonInstance == null){
            MemoryClientRepository.singletonInstance = new MemoryClientRepository();
        }
        return MemoryClientRepository.singletonInstance;
    }

    @Override
    public void save(Client client) {
        this.clients.add(client);
    }

    @Override
    public List<Client> getAll() {
        return this.clients;
    }

    @Override
    public void delete(Client client) {
        this.clients.remove(client);
    }
}
