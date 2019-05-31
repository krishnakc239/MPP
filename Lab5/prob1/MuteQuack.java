package lesson5.labs.prob1;

public class MuteQuack implements QuackBehavior{

	@Override
	public void quack() {
		System.out.println("Can not quack");
	}
	
}
