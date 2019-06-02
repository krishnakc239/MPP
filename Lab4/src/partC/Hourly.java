package partC;

public class Hourly extends Employee{
    private  double hourlyWage;
    private  int hoursPerWeek;

    Hourly(String empId, double hourlyWage, int hoursPerWeek){
        emp_id = empId;
        this.hourlyWage = hourlyWage;
        this.hoursPerWeek = hoursPerWeek;
    }

    @Override
    double calcGrossPay() {
        return hourlyWage* hoursPerWeek* 4;
    }
}
