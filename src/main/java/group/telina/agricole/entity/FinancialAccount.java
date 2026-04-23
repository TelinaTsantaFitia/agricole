package group.telina.agricole.entity;

import java.math.BigDecimal;

public class FinancialAccount {

    private String id;
    private String type;
    private BigDecimal balance;

    public FinancialAccount() {
    }

    public FinancialAccount(
            String id,
            String type,
            BigDecimal balance
    ) {
        this.id = id;
        this.type = type;
        this.balance = balance;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}