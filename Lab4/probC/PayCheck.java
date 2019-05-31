package lesson4.labs.probC;

public final class PayCheck {
	double grossPay;
	double fica = 23;
	double state = 5;
	double local = 1;
	double medicare = 3;
	double socialSecurity = 7.5;
	
	PayCheck(double d){
		grossPay=d;
	}
	
	void print() {
		System.out.println("Gross Payment\t:" + grossPay);
		System.out.println("Fica Tax\t:" + fica+" %");
		System.out.println("State Tax\t:" + state+ " %");
		System.out.println("Local Tax\t:" + local+" %");
		System.out.println("Medicare\t:" + medicare+" %");
		System.out.println("Social Security\t:" + socialSecurity+" %");
		System.out.println("-----------------------------------------");
		System.out.println("Net Pay\t\t:" + getNetPay());
		System.out.println("");
	}
	
	double getNetPay() {
		return grossPay*(100-fica-state-local-medicare-socialSecurity)/100;
	}
}
