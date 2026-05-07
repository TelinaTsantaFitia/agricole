// MemberStatRest.java
package group.telina.agricole.dto;
import java.math.BigDecimal;

public record MemberStatRest(
        String memberId,
        String firstName,
        String lastName,
        BigDecimal totalPaid,
        BigDecimal totalUnpaid
) {}