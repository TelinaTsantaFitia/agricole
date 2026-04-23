package group.telina.agricole.entity;

public class Account {

    private Integer id;
    private Integer collectivityId;
    private String type; // CASH / BANK / MOBILE
    private String provider;
    private String accountNumber;
    private Double balance;

    public Account() {}

    // =========================
    // GETTERS
    // =========================

    public Integer getId() {
        return id;
    }

    public Integer getCollectivityId() {
        return collectivityId;
    }

    public String getType() {
        return type;
    }

    public String getProvider() {
        return provider;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    // =========================
    // SETTERS
    // =========================

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCollectivityId(Integer collectivityId) {
        this.collectivityId = collectivityId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}