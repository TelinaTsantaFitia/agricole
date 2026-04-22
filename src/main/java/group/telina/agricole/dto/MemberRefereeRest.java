package group.telina.agricole.dto;

public class MemberRefereeRest {
    private String id;
    private String name;
    private String occupation;

    public MemberRefereeRest(String id, String name, String occupation) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getOccupation() { return occupation; }
}
