package Q3_B;

public class Circle{
	public Cylinder cylinder;
	
	Circle(double height, double radius) {
		cylinder = new Cylinder(height, radius);
	}

	public double computeArea() {
		double rad = cylinder.getRadius();
		return Math.PI * rad * rad;
	}
}
