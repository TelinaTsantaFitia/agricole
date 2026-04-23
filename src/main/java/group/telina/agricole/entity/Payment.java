package group.telina.agricole.entity;

import java.time.LocalDateTime;

public class Payment {

    private Integer id;
    private Integer memberId;
    private Integer collectivityId;
    private Double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private Integer accountId;

    public Payment() {}

    // =========================
    // GETTERS
    // =========================
    public Integer getId() {
        return id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public Integer getCollectivityId() {
        return collectivityId;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Integer getAccountId() {
        return accountId;
    }

    // =========================
    // SETTERS
    // =========================
    public void setId(Integer id) {
        this.id = id;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public void setCollectivityId(Integer collectivityId) {
        this.collectivityId = collectivityId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}