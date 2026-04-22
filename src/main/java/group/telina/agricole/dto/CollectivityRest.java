package group.telina.agricole.dto;

public class CollectivityRest {
    private Integer id; // Changé en Integer
    private String name;
    private String address;
    private String collectivityType;
    private String email;
    private Integer phoneNumber;

    public CollectivityRest(Integer id, String name, String address, String collectivityType, String email, Integer phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.collectivityType = collectivityType;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCollectivityType() { return collectivityType; }
    public String getEmail() { return email; }
    public Integer getPhoneNumber() { return phoneNumber; }
}