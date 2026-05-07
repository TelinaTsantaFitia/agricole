package group.telina.agricole.dto;

import java.math.BigDecimal;

public class MemberStatRest {
    private String memberId;
    private String firstName;
    private String lastName;
    private BigDecimal totalPaid;
    private BigDecimal totalUnpaid;
    private BigDecimal attendanceRate; // nouveau

    public MemberStatRest(String memberId, String firstName, String lastName,
                          BigDecimal totalPaid, BigDecimal totalUnpaid, BigDecimal attendanceRate) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPaid = totalPaid;
        this.totalUnpaid = totalUnpaid;
        this.attendanceRate = attendanceRate;
    }

    public String getMemberId() { return memberId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public BigDecimal getTotalPaid() { return totalPaid; }
    public BigDecimal getTotalUnpaid() { return totalUnpaid; }
    public BigDecimal getAttendanceRate() { return attendanceRate; }
}