package group.telina.agricole.dto;

public class CollectivityRest {

    private Integer id;
    private Integer number;
    private String name;
    private String address;
    private String collectivityType;

    public CollectivityRest(Integer id, Integer number, String name,
                            String address, String collectivityType) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.address = address;
        this.collectivityType = collectivityType;
    }

    public Integer getId() { return id; }
    public Integer getNumber() { return number; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCollectivityType() { return collectivityType; }
}