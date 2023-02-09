package solvd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private double balance;
    private boolean isActive;
    private int accountType_id;
    private int login_id;

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
