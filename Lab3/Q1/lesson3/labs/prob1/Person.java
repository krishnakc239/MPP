package lesson3.labs.prob1;


public class Person {

	private String name;
	Person(String n) {
		name = n;
	}
	public String getName() {
		return name;
	}
	@Override
	public boolean equals(Object aPerson) {
		if(aPerson == null) return false; 
		if(!(aPerson instanceof Person)) {
			if(!(aPerson instanceof PersonWithJob)) return false;
			PersonWithJob p = (PersonWithJob)aPerson;
			return this.name.equals(p.getName());
		}
		Person p = (Person)aPerson;
		boolean isEqual = this.name.equals(p.name);
		return isEqual;
	}
	public static void main(String[] args) {
		
	}

}
