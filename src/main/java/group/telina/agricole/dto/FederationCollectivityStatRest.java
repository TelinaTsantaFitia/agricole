// FederationCollectivityStatRest.java
package group.telina.agricole.dto;
import java.math.BigDecimal;

public record FederationCollectivityStatRest(
        String collectivityId,
        String collectivityName,
        BigDecimal percentageUpToDate,
        long newMembers
) {}