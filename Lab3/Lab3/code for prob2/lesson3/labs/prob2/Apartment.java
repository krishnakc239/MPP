package lesson3.labs.prob2;

public class Apartment {
	private String name;
	private double rent;
	
	Apartment(String name, double rent) {
		this.name = name;
		this.rent = rent;
	}
	
	public double getRent() {
		return rent;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("[" + name + ":" + rent + "]");
		return strBuilder.toString();
	}
}
