package ezracks.com.ezracks.Model;

/**
 * Created by aipxperts-ubuntu-01 on 11/4/17.
 */

public class Store {
    String id;
    String store_name;
    String address;
    String city;
    String state;
    String zipcode;
    String fl_store_to_be_reassigned;
    String store_code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getFl_store_to_be_reassigned() {
        return fl_store_to_be_reassigned;
    }

    public void setFl_store_to_be_reassigned(String fl_store_to_be_reassigned) {
        this.fl_store_to_be_reassigned = fl_store_to_be_reassigned;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code;
    }
}
