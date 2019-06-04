package business.domain;

public class LibraryMember extends Person {
    private String memberid;
//    private Address address;

    public LibraryMember(String memberid,Person person,Address address) {
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

//    @Override
//    public Address getAddress() {
//        return address;
//    }
//
//    @Override
//    public void setAddress(Address address) {
//        this.address = address;
//    }
}
