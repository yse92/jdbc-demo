package solvd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private String firstName;
    private String lastName;
    private String position;
    private float salary;
    private int branch_id;

    public Employee(String firstName, String lastName, String position, float salary, int branch_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.branch_id = branch_id;
    }

    public Employee() {

    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", branch_id=" + branch_id +
                '}';
    }
}
