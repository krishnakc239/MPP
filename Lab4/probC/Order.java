package lesson4.labs.probC;

import java.time.LocalDate;

public class Order {
	int orderNo;
	LocalDate orderDate;
	double orderAmount;
	
	Order(int ordN, LocalDate orderDt, double ordAmt){
		orderNo = ordN;
		orderDate = orderDt;
		orderAmount = ordAmt;
	}

}
