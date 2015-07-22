package cast.com.br.testeapp.model.entity;

import java.util.List;

import cast.com.br.testeapp.model.persistence.MemoryClientRepository;

/**
 * Created by Administrador on 20/07/2015.
 */
public class Client {

    private String name;
    private Integer age;
    private String address;

    private String phone;

    public void save(){
        MemoryClientRepository.getInstance().save((this));
    }

    public static List<Client> getAll(){
        return MemoryClientRepository.getInstance().getAll();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        return !(age != null ? !age.equals(client.age) : client.age != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}
