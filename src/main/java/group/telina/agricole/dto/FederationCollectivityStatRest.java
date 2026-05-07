package group.telina.agricole.dto;

import java.math.BigDecimal;

public class FederationCollectivityStatRest {
    private String collectivityId;
    private String collectivityName;
    private BigDecimal percentageUpToDate;
    private long newMembers;
    private BigDecimal attendanceRate; // nouveau

    public FederationCollectivityStatRest(String collectivityId, String collectivityName,
                                          BigDecimal percentageUpToDate, long newMembers,
                                          BigDecimal attendanceRate) {
        this.collectivityId = collectivityId;
        this.collectivityName = collectivityName;
        this.percentageUpToDate = percentageUpToDate;
        this.newMembers = newMembers;
        this.attendanceRate = attendanceRate;
    }

    public String getCollectivityId() { return collectivityId; }
    public String getCollectivityName() { return collectivityName; }
    public BigDecimal getPercentageUpToDate() { return percentageUpToDate; }
    public long getNewMembers() { return newMembers; }
    public BigDecimal getAttendanceRate() { return attendanceRate; }
}