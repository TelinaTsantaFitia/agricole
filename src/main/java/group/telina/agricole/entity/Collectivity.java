package group.telina.agricole.entity;

public class Collectivity {

    private Integer id;
    private Integer number;
    private String name;
    private String address;
    private String collectivityType;
    private String email;
    private Integer phoneNumber;

    public Collectivity() {}

    // getters & setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

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