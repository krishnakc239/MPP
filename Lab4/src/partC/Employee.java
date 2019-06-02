package partC;

public abstract class Employee {
    public String emp_id;

    public  PayCheck calcCompensation(){
        double grossSalry = calcGrossPay();
        return new PayCheck(grossSalry,0.23,0.05,0.01, 0.03,0.075);
    }

    abstract double calcGrossPay();
}
