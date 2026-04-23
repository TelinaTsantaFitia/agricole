package group.telina.agricole.entity;

import java.util.List;

public class Collectivity {

    private String id;
    private Integer number;
    private String name;
    private String address;
    private String collectivityType;

    private List<Member> members;


    public Collectivity() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number=number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }


    public String getCollectivityType() {
        return collectivityType;
    }

    public void setCollectivityType(
            String collectivityType
    ){
        this.collectivityType=collectivityType;
    }



    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(
            List<Member> members
    ){
        this.members=members;
    }

}