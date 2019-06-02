package application.domain;

public class Member extends Role {
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String memberNum;
    Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
