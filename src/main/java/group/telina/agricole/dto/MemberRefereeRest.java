package group.telina.agricole.dto;

public class MemberRefereeRest {
    private Integer id; // Changé de String à Integer
    private String name;
    private String occupation;

    public MemberRefereeRest(Integer id, String name, String occupation) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getOccupation() { return occupation; }
}