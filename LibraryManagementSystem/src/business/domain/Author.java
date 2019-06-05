package business.domain;

import java.awt.print.Book;

public class Author extends Person{
    private String credentails;
    private Book book;

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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
