package group.telina.agricole.dto;

import group.telina.agricole.entity.Member;
import java.util.List;

public class CollectivityRest {

    private String id;           // ← était Integer
    private Integer number;
    private String name;
    private String address;
    private String collectivityType;
    private List<Member> members; // ← NOUVEAU

    public CollectivityRest(String id, Integer number, String name,
                            String address, String collectivityType,
                            List<Member> members) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.address = address;
        this.collectivityType = collectivityType;
        this.members = members;
    }

    public String getId() { return id; }
    public Integer getNumber() { return number; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCollectivityType() { return collectivityType; }
    public List<Member> getMembers() { return members; }
}