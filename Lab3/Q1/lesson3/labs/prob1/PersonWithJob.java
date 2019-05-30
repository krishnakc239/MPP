package lesson3.labs.prob1;

public class PersonWithJob {// extends Person {
	private Person person;
	private double salary;
	
	public double getSalary() {
		return salary;
	}
	public String getName() {
		return person.getName();
	}	
	PersonWithJob(String n, double s) {
		//super(n);
		person = new Person(n);
		salary = s;
	}
	
	@Override
	public boolean equals(Object aPerson) {
		if(aPerson == null) return false; 
		if(!(aPerson instanceof PersonWithJob)) {
			if (!(aPerson instanceof Person)) return false;
			Person p = (Person)aPerson;
			return this.getName().equals(p.getName());
		}
		PersonWithJob p = (PersonWithJob)aPerson;
		boolean isEqual = this.getName().equals(p.getName()) &&
				this.getSalary()==p.getSalary();
		return isEqual;
	}
	public static void main(String[] args) {
		Object p1 = new PersonWithJob("Joe", 30000);
		Object p2 = new Person("Joe");
		//As PersonsWithJobs, p1 should be equal to p2
		System.out.println("p1.equals(p2)? " + p1.equals(p2));
		System.out.println("p2.equals(p1)? " + p2.equals(p1));
	}


}
