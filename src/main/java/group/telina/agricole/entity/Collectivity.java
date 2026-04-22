package group.telina.agricole.entity;

import java.util.Objects;

public class Collectivity {
    private Integer id; // Changé en Integer
    private String name;
    private String address;
    private String collectivityType;
    private String email;
    private Integer phoneNumber;

    public Collectivity() {}

    public Collectivity(Integer id, String name, String address, String collectivityType, String email, Integer phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.collectivityType = collectivityType;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Collectivity(String id, String name, String address, String collectivityType, String email, int phoneNumber) {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCollectivityType() { return collectivityType; }
    public void setCollectivityType(String collectivityType) { this.collectivityType = collectivityType; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(Integer phoneNumber) { this.phoneNumber = phoneNumber; }
}