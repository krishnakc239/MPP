package lesson4.labs.probC;

public class Hourly extends Employee{
	double hourlyWage;
	double hoursPerWeek;
	
	Hourly(double hw, double hpw){
		hourlyWage = hw;
		hoursPerWeek = hpw;
	}

	@Override
	double calcGrossPay() {
		return hourlyWage*hoursPerWeek*4;
	}

}
