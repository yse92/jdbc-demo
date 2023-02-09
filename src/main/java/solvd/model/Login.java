package solvd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
    private String name;
    private String password;
    private int customer_id;

    public Login(String name, String password, int customer_id) {
        this.name = name;
        this.password = password;
        this.customer_id = customer_id;
    }

    public Login() {
    }

    @Override
    public String toString() {
        return "Login{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", customerId='" + customer_id + '\'' +
                '}';
    }
}
