package lesson5.labs.prob4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustOrderFactory{
	
	public static class OrderImpl implements Order{
		private LocalDate orderDate;
		private List<Item> items;
		
		//use a factory method
		private OrderImpl(LocalDate orderDate) {
			this.orderDate = orderDate;
			items = new ArrayList<Item>();	
		}
		
		public static Order newOrder(LocalDate date){
			Order ord = new OrderImpl(date);
			return ord;
		}
		public void addItem(String name){
			items.add(new Item(name));
		}
		@Override
		public String toString() {
			return orderDate + ": " + 
		              items.toString();
		}
	}

	public static void createOrder(Customer cust, LocalDate date, String[] items) {
		if(cust == null) throw new NullPointerException("Null customer");
		Order ord = OrderImpl.newOrder(date);
		for(String s:items) {
			ord.addItem(s);
		}
		cust.addOrder(ord);
		//return ord;
	}
}
