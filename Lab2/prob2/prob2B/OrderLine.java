package prob2B;

import java.util.Date;

public class OrderLine {
	int lineNum;
	double price;
	int quantity;
	Date orderDate;
	
	public void setOrder(int ln, double pr, int qty, Date dt) {
		lineNum = ln;
		price = pr;
		quantity = qty;
		orderDate = dt;
	}

	public int getLineNum() {
		return lineNum;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

}
