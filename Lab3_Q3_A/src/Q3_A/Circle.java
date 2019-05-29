package Q3_A;

public class Circle extends Cylinder{


	Circle(double height, double radius) {
		super(height, radius);
	
	}

	public double computeArea() {
		double rad = super.getRadius();
		return Math.PI * rad * rad;
	}
}
