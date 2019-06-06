package business.domain;

public class Member extends Person {
    private String memberid;
    private CheckoutRecord checkoutRecord;
//    private Address address;

    public Member(String memberid, Person person, Address address) {
        super(person,address);
//        this.address = address;
        this.memberid = memberid;
        checkoutRecord = new CheckoutRecord();
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

    public CheckoutRecord getCheckoutRecord() {
        if (checkoutRecord == null){
            return new CheckoutRecord();
        }
        return checkoutRecord;
    }

    public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
        this.checkoutRecord = checkoutRecord;
    }

    @Override
    public boolean equals(Object ob) {
        if(ob == null) return false;
        if(ob.getClass() != getClass()) return false;
        Member m = (Member) ob;
        return m.memberid.equals(memberid);
    }
}
