package lesson3.labs.prob4;

public class House extends Property {
	{
		System.out.println("House obj created...");
	}
	private int lotSize;
	
	House(int ls){
		lotSize = ls;
	}
	
	@Override
	public double computeRent() {
		
		return 0.1*lotSize;
	}

}
