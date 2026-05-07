package group.telina.agricole.entity;

public class Attendance {

    private Integer id;
    private String activityId;
    private String memberId;
    private boolean present;

    public Attendance() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getActivityId() { return activityId; }
    public void setActivityId(String activityId) { this.activityId = activityId; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public boolean isPresent() { return present; }
    public void setPresent(boolean present) { this.present = present; }
}