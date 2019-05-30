package prob2B;

import java.util.ArrayList;
import java.util.List;

public class Order {
	int orderNum;
	List<OrderLine> orderLines;
	
	Order(OrderLine ord){
		orderLines = new ArrayList<OrderLine>();
		addOrderLine(ord);
	}
	
	public List<OrderLine> getOrderLine(){
		return orderLines;
	}
	
	public void addOrderLine(OrderLine ord) {
		orderLines.add(ord);
	}

}
