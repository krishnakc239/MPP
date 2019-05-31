package lesson4.labs.probC;

public class Salaried extends Employee{
	double salary;
	
	Salaried(double sal){
		salary = sal;
	}
	
	@Override
	double calcGrossPay() {
		return salary;
	}
	

}
