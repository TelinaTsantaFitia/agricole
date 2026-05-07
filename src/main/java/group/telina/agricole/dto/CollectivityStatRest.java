// CollectivityStatRest.java
package group.telina.agricole.dto;
import java.util.List;

public record CollectivityStatRest(
        String collectivityId,
        List<MemberStatRest> memberStats
) {}