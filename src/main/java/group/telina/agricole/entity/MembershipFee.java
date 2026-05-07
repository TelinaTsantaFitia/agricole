package group.telina.agricole.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MembershipFee {

    private String id;
    private String label;
    private String status;
    private String frequency;
    private BigDecimal amount;
    private LocalDate eligibleSince;
    private String collectivityId;

    public MembershipFee() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getEligibleSince() { return eligibleSince; }
    public void setEligibleSince(LocalDate eligibleSince) { this.eligibleSince = eligibleSince; }

    public String getCollectivityId() { return collectivityId; }
    public void setCollectivityId(String collectivityId) { this.collectivityId = collectivityId; }
}