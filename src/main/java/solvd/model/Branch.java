package solvd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Branch {
    private String phone;
    private String adress;

    public Branch(String phone, String adress) {
        this.phone = phone;
        this.adress = adress;
    }

    public Branch() {

    }

    @Override
    public String toString() {
        return "Branch{" +
                "phone='" + phone + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}

