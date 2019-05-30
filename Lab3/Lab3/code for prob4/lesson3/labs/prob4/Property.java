package lesson3.labs.prob4;

public abstract class Property {
	private Address add;
	
	public abstract double computeRent();
	
	public void setAddress(Address add0) {
		add = add0;
	}
	
	public Address getAddress() {
		return add;
	}

}
