package group.telina.agricole.entity;

public class Account {

    private String id;
    private String accountType;
    private String provider;      // Added to fix "cannot find symbol"
    private String accountNumber; // Added to fix "cannot find symbol"
    private double balance;
    private String collectivityId;

    public Account() {}

    public Account(String id, String accountType, String provider,
                   String accountNumber, double balance, String collectivityId) {
        this.id = id;
        this.accountType = accountType;
        this.provider = provider;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.collectivityId = collectivityId;
    }

    // --- GETTERS ---

    public String getId() { return id; }

    // This matches the a.getType() call in your repository
    public String getType() { return accountType; }

    public String getProvider() { return provider; }

    public String getAccountNumber() { return accountNumber; }

    public double getBalance() { return balance; }

    public String getCollectivityId() { return collectivityId; }

    // --- SETTERS ---

    public void setId(String id) { this.id = id; }

    // Overload or replace if your DB returns an Int for the ID
    public void setId(int id) { this.id = String.valueOf(id); }

    public void setAccountType(String accountType) { this.accountType = accountType; }

    public void setProvider(String provider) { this.provider = provider; }

    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public void setBalance(double balance) { this.balance = balance; }

    public void setCollectivityId(String collectivityId) { this.collectivityId = collectivityId; }
}