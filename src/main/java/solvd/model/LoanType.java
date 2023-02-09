package solvd.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoanType {
    private String description;

    public LoanType() {
    }

    public LoanType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LoanType{" +
                "description='" + description + '\'' +
                '}';
    }
}
