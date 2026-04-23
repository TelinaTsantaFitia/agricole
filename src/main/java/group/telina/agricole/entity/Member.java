package group.telina.agricole.entity;

public class Member {

    private String id;
    private String firstName;
    private String lastName;
    private String collectivityId;
    private String occupation;


    public Member() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
    }


    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

}