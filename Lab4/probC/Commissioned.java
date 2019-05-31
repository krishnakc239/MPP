package lesson4.labs.probC;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Commissioned extends Employee{
	double commission;
	double baseSalary;
	List<Order> orders;
	
	Commissioned(double com, double bsal){
		orders = new ArrayList<Order>();
		commission = com;
		baseSalary = bsal;
	}
	public void addOrder(Order ord) {
		orders.add(ord);
	}
	
	@Override
	double calcGrossPay() {
		double totalOrderAmt = 0;
		int prevMonth = LocalDate.now().getMonthValue()-1;
		for(Order o: orders) {
			if(o.getMonth()==prevMonth) {
				totalOrderAmt+=o.orderAmount;
			}
		}
		
		return baseSalary+commission*totalOrderAmt;
	}

}
