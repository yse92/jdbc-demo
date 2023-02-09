package solvd.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountBranch {
    private int accountID;
    private int branchID;

    public AccountBranch() {
    }

    public AccountBranch(int accountID, int branchID) {
        this.accountID = accountID;
        this.branchID = branchID;
    }

    @Override
    public String toString() {
        return "AccountBranch{" +
                "accountID=" + accountID +
                ", branchID=" + branchID +
                '}';
    }

}
