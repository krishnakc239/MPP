package lesson3.labs.prob2;

import java.util.ArrayList;
import java.util.List;

public class Building {
	private String name;
	private double maintenanceCost;
	List<Apartment> apartments;
	
	Building(String name, double maintenanceCost) {
		this.name = name;
		this.maintenanceCost = maintenanceCost;
		this.apartments = new ArrayList<>();
	}
	
	public void addApartment(String name, double rent) {
		Apartment apartment = new Apartment(name, rent);
		apartments.add(apartment);
	}
	
	public String getName() {
		return name;
	}
	
	double calucateProfit() {
		double profit = 0;
		for(Apartment apartment : apartments) {
			profit += apartment.getRent();
		}
		return profit - maintenanceCost;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("[" + name + "]:");
		for(Apartment apartment : apartments) {
			strBuilder.append(apartment);
		}
		return strBuilder.append(" - " + maintenanceCost + "\n").toString();
	}
}
