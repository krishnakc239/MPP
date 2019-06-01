package lesson5.labs.prob4.extpackage;

import java.time.LocalDate;

import lesson5.labs.prob4.CustOrderFactory;
import lesson5.labs.prob4.Customer;
import lesson5.labs.prob4.CustOrderFactory;

public class Main {
	public static void main(String[] args) {
		Customer cust = new Customer("Bob");
		//cust.addOrder(Order.newOrder(cust,LocalDate.now()));
		//cust.addOrder(CustOrderFactory.createOrder("Shirt","Laptop"));
		CustOrderFactory.createOrder(cust, LocalDate.now(), new String[]{"Shirt","Laptop"});
		CustOrderFactory.createOrder(cust, LocalDate.now(), new String[]{"Pants","Knife set"});
/*		Order order = Order.newOrder(cust, LocalDate.now());
		order.addItem("Shirt");
		order.addItem("Laptop");

		order = Order.newOrder(cust, LocalDate.now());
		order.addItem("Pants");
		order.addItem("Knife set");*/
		System.out.println(cust.getOrders());
	}
}

		
