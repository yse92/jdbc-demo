package solvd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private Double balance;
    private Boolean isActive;
    private Integer accountType_id;
    private Integer login_id;

    public Account() {
    }

    public Account(double balance, boolean isActive, int accountType_id, int login_id) {
        this.balance = balance;
        this.isActive = isActive;
        this.accountType_id = accountType_id;
        this.login_id = login_id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", isActive=" + isActive +
                ", accountType_id=" + accountType_id +
                ", login_id=" + login_id +
                '}';
    }
}
