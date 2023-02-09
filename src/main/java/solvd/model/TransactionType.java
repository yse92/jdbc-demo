package solvd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionType {
    private String description;

    public TransactionType() {
    }

    public TransactionType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "description='" + description + '\'' +
                '}';
    }
}
