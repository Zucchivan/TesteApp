package cast.com.br.testeapp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

import cast.com.br.testeapp.model.persistence.MemoryClientRepository;
import cast.com.br.testeapp.model.persistence.SQLiteClientRepository;

/**
 * Created by Administrador on 20/07/2015.
 */
public class Client implements Parcelable {

    private Integer id;
    private String name;
    private Integer age;
    private String phone;
    private String zipCode;
    private String streetType;
    private String street;
    private String neighborhood;
    private String city;
    private String state;

    public Client(){  super();  }

    public Client(Parcel in){
        super();
        readToParcel(in);
    }

    public void save(){
        SQLiteClientRepository.getInstance().save(this);
    }

    public static List<Client> getAll(){
        return SQLiteClientRepository.getInstance().getAll();
    }

    public void delete() {
        SQLiteClientRepository.getInstance().delete(this);
    }

    public static final Parcelable.Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

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

        if (id != null ? !id.equals(client.id) : client.id != null) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (age != null ? !age.equals(client.age) : client.age != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        if (zipCode != null ? !zipCode.equals(client.zipCode) : client.zipCode != null) return false;
        if (streetType != null ? !streetType.equals(client.streetType) : client.streetType != null) return false;
        if (street != null ? !street.equals(client.street) : client.street != null) return false;
        if (neighborhood != null ? !neighborhood.equals(client.neighborhood) : client.neighborhood != null) return false;
        if (city != null ? !city.equals(client.city) : client.city != null) return false;
        return !(state != null ? !state.equals(client.state) : client.state != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (streetType != null ? streetType.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (neighborhood != null ? neighborhood.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id == null ? -1 : this.id);
        dest.writeString(this.name == null ? "" : this.name);
        dest.writeInt(this.age == null ? -1 : this.age);
        dest.writeString(this.phone == null ? "" : this.phone);
        dest.writeString(this.zipCode == null ? "" : this.zipCode);
        dest.writeString(this.streetType == null ? "" : this.streetType);
        dest.writeString(this.street == null ? "" : this.street);
        dest.writeString(this.neighborhood == null ? "" : this.neighborhood);
        dest.writeString(this.city == null ? "" : this.city);
        dest.writeString(this.state == null ? "" : this.state);
    }

    private void readToParcel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        int partialAge = in.readInt();
        age = partialAge == -1 ? null : partialAge;
        phone = in.readString();
        zipCode = in.readString();
        streetType = in.readString();
        street = in.readString();
        neighborhood = in.readString();
        city = in.readString();
        state = in.readString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
