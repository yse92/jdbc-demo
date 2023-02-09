package solvd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionErrorLog {
    private String description;

    public TransactionErrorLog() {
    }

    public TransactionErrorLog(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TransactionErrorLog{" +
                "description='" + description + '\'' +
                '}';
    }
}
