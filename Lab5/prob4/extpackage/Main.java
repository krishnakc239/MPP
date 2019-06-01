package prob4.extpackage;

import java.time.LocalDate;

import prob4.CustOrderFactory;
import prob4.CustOrderFactory.OrderImpl;
import prob4.Customer;
import prob4.Order;

public class Main {
	public static void main(String[] args) {
		Customer cust = CustOrderFactory.createCustomer("Bob");
		Order ord1 = CustOrderFactory.createOrder(LocalDate.now(), new String[]{"Shirt","Laptop"});
		Order ord2 = CustOrderFactory.createOrder(LocalDate.now(), new String[]{"Pants","Knife set"});
		cust.addOrder(ord1);
		cust.addOrder(ord2);
		//cust.addOrder(Order.newOrder(cust,LocalDate.now()));
		//cust.addOrder(CustOrderFactory.createOrder("Shirt","Laptop"));
		
/*		Order order = Order.newOrder(cust, LocalDate.now());
		order.addItem("Shirt");
		order.addItem("Laptop");

		order = Order.newOrder(cust, LocalDate.now());
		order.addItem("Pants");
		order.addItem("Knife set");*/
		
		System.out.println(cust.getOrders());
	}
}

		
