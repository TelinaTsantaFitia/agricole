package group.telina.agricole.entity;

import java.util.List;

public class Member {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String collectivityId;

    private List<Sponsor> sponsors;

    public Member() {}

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollectivityId() { return collectivityId; }

    public void setCollectivityId(String collectivityId) { this.collectivityId = collectivityId; }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }
}