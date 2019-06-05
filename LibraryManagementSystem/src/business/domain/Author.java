package business.domain;

import java.awt.print.Book;

public class Author extends Person{
    private String credentails;
    private String shortBio;
    private Book book;

    public Author(Person person,String credentails, Address address) {
        super(person, address);
        this.credentails = credentails;
    }
    
    public Author(Person person, String credentails, String shortBio) {
        super(person);
        this.credentails = credentails;
        this.shortBio = shortBio;
    }


    public String getCredentails() {
        return credentails;
    }

    public void setCredentails(String credentails) {
        this.credentails = credentails;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    @Override
    public boolean equals(Object ob) {
        if(ob == null) return false;
        if(!(ob instanceof Author)) return false;
        Author author = (Author)ob;
        return author.getFirstName().equals(this.getFirstName()) &&
        		author.getLastName().equals(this.getLastName()) &&
        		author.getPhone().equals(this.getPhone());
    }

    
    @Override
    public String toString() {
    	return "[" + this.getFirstName() + ", " +this.getLastName() + ", " +this.getPhone() + "]";
    }
    
}
