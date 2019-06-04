package business.domain;

public class Author extends Person{
    private String credentails;

    public Author(Person person,String credentails, Address address) {
        super(person, address);
        this.credentails = credentails;
    }


    public String getCredentails() {
        return credentails;
    }

    public void setCredentails(String credentails) {
        this.credentails = credentails;
    }
}
