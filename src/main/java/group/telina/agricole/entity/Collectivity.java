package group.telina.agricole.entity;

import java.util.List;

public class Collectivity {

    private String id;           // VARCHAR(20) en base
    private Integer number;
    private String name;
    private String address;      // = colonne "locality"
    private String collectivityType; // = colonne "specialization"
    private List<Member> members;    // pour GET /collectivities/{id}

    public Collectivity() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCollectivityType() { return collectivityType; }
    public void setCollectivityType(String t) { this.collectivityType = t; }

    public List<Member> getMembers() { return members; }
    public void setMembers(List<Member> members) { this.members = members; }
}