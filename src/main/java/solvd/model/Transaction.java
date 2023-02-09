package solvd.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
public class Transaction {
    private double amount;
    private Date transaction_date;
    private int transactionType_id;
    private int account_id;
    private int transactionErrorLog_id;
    private int employee_id;

    public Transaction() {
    }

    public Transaction(double amount, Date transaction_date, int transactionType_id, int account_id, int transactionErrorLog_id, int employee_id) {
        this.amount = amount;
        this.transaction_date = transaction_date;
        this.transactionType_id = transactionType_id;
        this.account_id = account_id;
        this.transactionErrorLog_id = transactionErrorLog_id;
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", transaction_date=" + transaction_date +
                ", transactionType_id=" + transactionType_id +
                ", account_id=" + account_id +
                ", transactionErrorLog_id=" + transactionErrorLog_id +
                ", employee_id=" + employee_id +
                '}';
    }
}
