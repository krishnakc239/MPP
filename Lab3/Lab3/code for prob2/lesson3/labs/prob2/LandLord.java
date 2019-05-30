package lesson3.labs.prob2;

import java.util.ArrayList;
import java.util.List;

public class LandLord {
	private String name;
	private List<Building> buildings;
	
	public LandLord(String name) {
		this.name = name;
		this.buildings = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public Building addBuilding(String name, double maintenanceCost) {
		Building building = new Building(name, maintenanceCost);
		buildings.add(building);
		return building;
	}

	public double monthlyProfit() {
		double profit = 0;
		for(Building building : buildings) {
			profit += building.calucateProfit();
		}
		return profit;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("[" + name + "] earns " + monthlyProfit() + " monthly, from:\n");
		for(Building building : buildings) {
			strBuilder.append(building);
		}
		return strBuilder.toString();
	}
}
