package business.domain;

import java.io.Serializable;
import java.util.*;

final public class Book implements Serializable {

    private static final long serialVersionUID = 6110690276685962829L;
    private List<BookCopy> bookCopies = new ArrayList<>();
//    private BookCopy[] copies;
    private List<Author> authors;
    private String isbn;
    private String title;
    private int maxCheckoutLength;

    public Book(){}
    public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.maxCheckoutLength = maxCheckoutLength;
        this.authors = Collections.unmodifiableList(authors);
//        bookCopies.add(new BookCopy[]{new BookCopy(this, 1, true));
        bookCopies.add(new BookCopy(this,1,true));

    }

//    public void updateCopies(BookCopy copy) {
//        for(int i = 0; i < copies.length; ++i) {
//            BookCopy c = copies[i];
//            if(c.equals(copy)) {
//                copies[i] = copy;
//
//            }
//        }
//        bookCopies.contains(copy)? bookCopies.re
//    }
    public int getCopiesNumber(){
        return bookCopies.size();
    }
    public List<Integer> getCopyNums() {
        List<Integer> retVal = new ArrayList<>();
        for(BookCopy c : bookCopies) {
            retVal.add(c.getCopyNum());
        }
        return retVal;
    }

    public void addCopy() {
//        BookCopy[] newArr = new BookCopy[copies.length + 1];
//        System.arraycopy(copies, 0, newArr, 0, copies.length);
//        newArr[copies.length] = new BookCopy(this, copies.length +1, true);
//        copies = newArr;
        bookCopies.add(new BookCopy(this,bookCopies.size()+1,true));
    }

    public void reduceCopy(BookCopy checkedBookCopy){
        if (bookCopies.contains(checkedBookCopy)){
            System.out.println("book copy contain selected book copy");
            bookCopies.remove(checkedBookCopy);
        }
    }

    @Override
    public boolean equals(Object ob) {
        if(ob == null) return false;
        if(ob.getClass() != getClass()) return false;
        Book b = (Book)ob;
        return b.isbn.equals(isbn);
    }


    public boolean isAvailable() {
        if(bookCopies == null) {
            return false;
        }
        return bookCopies.stream()
                .map(l -> l.isAvailable())
                .reduce(false, (x,y) -> x || y);
    }



    public BookCopy getNextAvailableCopy() {
        Optional<BookCopy> optional
                = bookCopies.stream()
                .filter(x -> x.isAvailable()).findFirst();
        return optional.isPresent() ? optional.get() : null;
    }

    public BookCopy getCopy(int copyNum) {
        for(BookCopy c : bookCopies) {
            if(copyNum == c.getCopyNum()) {
                return c;
            }
        }
        return null;
    }

    public BookCopy getBookCopyFromBook(Book book){

        System.out.println("copies length == "+ book.getCopies().size());
        List<BookCopy> bookCopyList = book.getCopies();
//        BookCopy[] bookCopies = book.getCopies();
        for (int i=0;i<= bookCopyList.size();i++){
            if (bookCopyList.get(i).getBook().getIsbn().equals(book.isbn)){
                bookCopyList.get(i).setBook(book);
                return bookCopyList.get(i);
            }
        }
        return null;
    }


    public List<Author> getAuthors() {
        return authors;
    }

    public List<BookCopy> getCopies() {
        return bookCopies;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getIsbn(){
        return isbn;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxCheckoutLength(){
        return maxCheckoutLength;
    }

    public void setMaxCheckoutLength(int maxCheckoutLength){
        this.maxCheckoutLength = maxCheckoutLength;
    }


    @Override
    public String toString() {
        return "isbn: " + isbn + ", maxLength: " + maxCheckoutLength + ", available: " + isAvailable();
    }

    public List<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public void setBookCopies(List<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }
}

