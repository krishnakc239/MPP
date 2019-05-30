package lesson3.labs.prob4;

public class Address {
	private String street;
	private String city;
	private String state;
	private String zip;
	
	Address(String str, String cty, String sta, String zp){
		street = str;
		city = cty;
		state = sta;
		zip = zp;
	}
	
	public void display() {
		System.out.println("Street: "+street);
		System.out.println("City: "+city);
		System.out.println("State: "+state);
		System.out.println("Zip: "+zip);
	}

}
