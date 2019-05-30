package lesson3.labs.prob2.external;

import lesson3.labs.prob2.Building;
import lesson3.labs.prob2.LandLord;

public class Driver {

	public static void main(String[] args) {
		LandLord landlordBen = new LandLord("Ben");
		Building BenA =  landlordBen.addBuilding("BenA", 10);
		BenA.addApartment("BenA1", 5);
		BenA.addApartment("BenA2", 10);
		BenA.addApartment("BenA3", 15);
		Building BenB =  landlordBen.addBuilding("BenB", 11);
		BenB.addApartment("BenB1", 2);
		BenB.addApartment("BenB2", 3);
		BenB.addApartment("BenB3", 4);
		System.out.println(landlordBen);
	}
}
