package external;

import java.util.Arrays;
import java.util.List;

import prob3.Vehicle;
import prob3.VehicleFactory;

public class App {
	
	public static void main(String args[]) {
		List<String> vehicles = Arrays.asList("Car", "Truck", "Bus", "ElectricCar", "Duck");
		for (String vehicle : vehicles) {
			System.out.print("[" + vehicle + "]");
			Vehicle v = VehicleFactory.getVehicle(vehicle);
			if (v != null) v.startEngine();
		}
	}

}
