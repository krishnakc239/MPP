package application.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Member extends Role  implements Serializable{

    private String firstName;
    private String lastName;
    private String phoneNum;
    private String memberNum;
    Address address;
    private String street;
    private String city;
    private String zip;
    private String state;


    public Member(){

    }
    public Member(String fname, String lname, String role){
        this.firstName = fname;
        this.lastName = lname;
        super.setRole(role);
    }

    public Member(String firstName, String lastName, String phoneNum, String memberNum, String role, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.memberNum = memberNum;
        this.address = address;
        super.setRole(role);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public  String getStreet(){
        return getAddress().getStreet();
    }

    public void setStreet(Address address){
        this.address.setStreet(address.getStreet());
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "first name="+ firstName+" last name"+ lastName +" phone Num="+ phoneNum + " state="+ getAddress().getState()+" city="+ getAddress().getCity()
                +" zip ="+ getAddress().getZip()+" street="+ getAddress().getStreet();
    }

    public String getCity() {
        return getAddress().getCity();
    }

    public void setCity(Address ad) {
        this.address.setCity(ad.getCity());
    }

    public String getZip() {
        return getAddress().getZip();
    }

    public void setZip(Address add) {
        this.address.setZip(add.getZip());
    }

    public String getState() {
        return getAddress().getState();
    }

    public void setState(Address add) {
        this.address.setState(add.getState());
    }
}
