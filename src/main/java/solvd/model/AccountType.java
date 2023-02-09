package solvd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountType {
    private String description;

    public AccountType() {

    }

    public AccountType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "description='" + description + '\'' +
                '}';
    }
}
