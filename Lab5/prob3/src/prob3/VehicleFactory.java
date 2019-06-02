package prob3;

public class VehicleFactory {
	private VehicleFactory() {
		
	}
	public static Vehicle getVehicle(String v) {
		if (v.equals("Car")) return new Car();
		if (v.equals("Bus")) return new Bus();
		if (v.equals("Truck")) return new Truck();
		if (v.equals("ElectricCar")) return new ElectricCar();
		return null;
	}
}
