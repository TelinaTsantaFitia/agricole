package group.telina.agricole.dto;

import java.util.List;

public class MemberRest {
    private Integer id; // Changé de String à Integer
    private String firstName;
    private String lastName;
    private String email;
    private String occupation;
    private Integer phoneNumber;
    private List<MemberRefereeRest> referees;

    public MemberRest(Integer id, String firstName, String lastName, String email,
                      String occupation, Integer phoneNumber, List<MemberRefereeRest> referees) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.occupation = occupation;
        this.phoneNumber = phoneNumber;
        this.referees = referees;
    }

    public Integer getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getOccupation() { return occupation; }
    public Integer getPhoneNumber() { return phoneNumber; }
    public List<MemberRefereeRest> getReferees() { return referees; }
}