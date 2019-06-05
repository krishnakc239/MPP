package business.domain;

import java.io.Serializable;

public class Address implements Serializable{
    private String state;
    private String city;
    private String zip;
    private String street;
    Member member;

    public Address(String state, String city, String zip, String street) {
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.street = street;
    }
    
    Address(String address) {
    	String[] addressArr = address.split(",");
    	if (addressArr.length == 4) {
	        this.state =  addressArr[2].trim();
	        this.city =  addressArr[1].trim();
	        this.zip =  addressArr[3].trim();
	        this.street = addressArr[0].trim();
    	}
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    @Override
    public String toString() {
        return "State :"+ state +" city :"+ city +" zip :"+zip+" street: "+ street;
    }
}
