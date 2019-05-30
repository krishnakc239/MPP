package lesson3.labs.prob4;

public class Condo extends Property {
	private int floorCount;
	
	Condo(int f){
		floorCount = f;
	}

	@Override
	public double computeRent() {
		
		return 400*floorCount;
	}

}
