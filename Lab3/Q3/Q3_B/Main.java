package Q3_B;

public class Main {
	public static void main(String args[]) {
		Circle c = new Circle(3.0,4.0);
		System.out.println("area of circle :"+ c.computeArea());
		Cylinder cyn = new Cylinder(4.0,4.0);
		System.out.println("volume of cylinder :"+ cyn.computeVolume());
	}

}
