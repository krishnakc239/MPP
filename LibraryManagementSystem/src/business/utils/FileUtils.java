package business.utils;

import business.domain.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils implements Serializable{

    static FileOutputStream fileOutputStream =null;
    static FileInputStream fileInputStream = null;
    static ObjectInputStream input = null;
    static List<Member> memberList = new ArrayList<>();
    public static final String OUTPUT_DIR = System.getProperty("user.dir")+"/src/dataaccess/storage/";

    public static <T> List<T> getObjectFromFile(Class<T> tClass){
        List<T> userList = new ArrayList<>();
        String direc = OUTPUT_DIR+getSplittedString(tClass.toString());

        try {
            //Read from the stored class file
            fileInputStream = new FileInputStream(new File(direc));
            input = new ObjectInputStream(fileInputStream);
            try {
                while (fileInputStream.available() > 0) {
                    //Read object from file
                    T user = (T) input.readObject();
                    userList.add(user);
                }
            } catch (EOFException ex) {
                ex.printStackTrace();
            }
            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static void loadUserLogins(){

        // Store Serialized User Object in File
        List<User> userList = new ArrayList<>();
        userList.add(new User("krishna","123","LIBRARIAN"));
        userList.add(new User("sujiv","1234","ADMIN"));
        userList.add(new User("thong","12345","BOTH"));
        writeObjectToFile(userList);

    }


    public static void loadLibraryMembers(){
        memberList.add(new Member("1111",new Person("Krishna","KC","12345"),new Address("IOWA","Fairfield","52257","100 N")));
        memberList.add(new Member("2222",new Person("Sujiv","Shrestha","45678"),new Address("DAKOTA","Fairfield","52257","100 N")));
        memberList.add(new Member("3333",new Person("Thong","Huang","7890"),new Address("TEXAS","Fairfield","52257","100 N")));
        writeObjectToFile(memberList);

    }

    public static void loadBooks(){
        List<Book> bookList = new ArrayList<>();
        // Store Serialized User Object in File
        bookList.add(new Book("1111", "Title 1", 10, Arrays.asList(
                new Author(new Person("firstN1","lastN1","12345"), "Cred1", new Address("IOWA1","Fairfield1","52257","100 N")),
                new Author(new Person("firstN2","lastN2","12345"), "Cred1", new Address("IOWA2","Fairfield2","52257","100 N")))));
//        bookList.add(new Book("1111", "Title 1", 10, Arrays.asList(
//                new Author(new Person("firstN1","lastN1","12345"), "Cred1", new Address("IOWA1","Fairfield1","52257","100 N")),
//                new Author(new Person("firstN2","lastN2","12345"), "Cred1", new Address("IOWA2","Fairfield2","52257","100 N")))));
//        bookList.add(new Book("1111", "Title 1", 10, Arrays.asList(
//                new Author(new Person("firstN1","lastN1","12345"), "Cred1", new Address("IOWA1","Fairfield1","52257","100 N")),
//                new Author(new Person("firstN2","lastN2","12345"), "Cred1", new Address("IOWA2","Fairfield2","52257","100 N")))));
        bookList.add(new Book("1112", "Title 2", 10, Arrays.asList(
                new Author(new Person("Author1","LastAuthorName1","12345"), "Cred1", new Address("IOWA","Fairfield","52257","100 N")),
                new Author(new Person("Author2","LastAuthorName2","12345"), "Cred1", new Address("IOWA","Fairfield","52257","100 N")))));
        writeObjectToFile(bookList);

    }

    public static <T> void writeObjectToFile(List<T> serObjList) {
        //to check the class name of object to make respective file
        String className = serObjList.get(0).getClass().toString();
        String dirctory = OUTPUT_DIR+getSplittedString(className); //eg output_dir+USER

        try {
            fileOutputStream = new FileOutputStream(dirctory);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOutputStream);

            for (Object o: serObjList){
                objectOut.writeObject(o);
            }
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getSplittedString(String string){
        int i = string.lastIndexOf(".")+1;
        return string.substring(i).toUpperCase();
    }

    public static Member findMemberBId(String memberId) {
        List<Member> memberList = getObjectFromFile(Member.class);
        for (Member m:memberList) {
            if (m.getMemberid().equalsIgnoreCase(memberId)){
                return m;
            }
        }
        return null;
    }

    public static Book findBookById(String isbn){
        List<Book> bookList = FileUtils.getObjectFromFile(Book.class);
        for (Book b: bookList){
            if (b.getIsbn().equalsIgnoreCase(isbn)){
                return b;
            }
        }
        return null;
    }
}
