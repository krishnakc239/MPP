package prob1;

public abstract class Duck {
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	
	public void swim() {
		System.out.println("Swimming");
	}
	
	void fly() {
		flyBehavior.fly();
	}
	
	void quack() {
		quackBehavior.quack();
	}
	
	void display() {
		System.out.println("Displaying");
	}

}
