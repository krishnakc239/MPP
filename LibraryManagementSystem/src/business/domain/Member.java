package business.domain;

public class Member extends Person {
    private String memberid;
//    private Address address;

    public Member(String memberid, Person person, Address address) {
        super(person,address);
//        this.address = address;
        this.memberid = memberid;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    @Override
    public String toString() {
        return memberid+" "+getFirstName()+" "+getLastName()+" "+getPhone()+" "+getAddress().getState()+" "+getAddress().getCity()+" "+getAddress().getZip()+" "+getAddress().getStreet();
    }
}
