package partC;

public class Salaried extends Employee {
    private double salary;

    Salaried(String  emp_id, double salary){
        super.emp_id = emp_id;
        this.salary = salary;

    }

    @Override
    double calcGrossPay(int m, int y) {
        return 0;
    }
}
