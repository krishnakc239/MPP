package lesson4.labs.probC;

public abstract class Employee {
	int empId;
	
	void print() {
		
	}
	
	PayCheck calcCompensation() {
		PayCheck pc = new PayCheck(calcGrossPay());
		return pc;
	}
	
	abstract double calcGrossPay();

}
