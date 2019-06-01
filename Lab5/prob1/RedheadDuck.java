package prob1;

public class RedheadDuck extends Duck {

	RedheadDuck(){
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}
	
	/*
	 * @Override void display() { 
	 * 		System.out.println("Displaying now Redhead Duck");
	 * }
	 */

}
