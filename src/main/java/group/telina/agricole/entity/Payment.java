package group.telina.agricole.entity;

import java.time.LocalDate;

public class Payment {

    private Integer id;
    private String memberId;        // ← String
    private String collectivityId;  // ← String
    private String accountId;       // ← String
    private Double amount;
    private LocalDate paymentDate;
    private String paymentMethod;

    public Payment() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getCollectivityId() { return collectivityId; }
    public void setCollectivityId(String collectivityId) { this.collectivityId = collectivityId; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}