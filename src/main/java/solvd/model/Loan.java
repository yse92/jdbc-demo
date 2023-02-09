package solvd.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Loan {
    private double amount;
    private String status;
    private int loanType_id;
    private int account_id;
    private int employee_id;

    public Loan() {
    }

    public Loan(double amount, String status, int loanType_id, int account_id, int employee_id) {
        this.amount = amount;
        this.status = status;
        this.loanType_id = loanType_id;
        this.account_id = account_id;
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "amount=" + amount +
                ", status='" + status + '\'' +
                ", loanType_id=" + loanType_id +
                ", account_id=" + account_id +
                ", employee_id=" + employee_id +
                '}';
    }
}
