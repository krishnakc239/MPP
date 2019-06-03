package application.utils;

import application.domain.Address;
import application.domain.Member;
import application.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils implements Serializable{

    static FileOutputStream fileOutputStream =null;
    static FileInputStream fileInputStream = null;
    static ObjectInputStream input = null;
    public static final String USER_OUTPUT_DIR = System.getProperty("user.dir")
            +"/src/dataaccess/storage/user.txt";
    public static final String OUTPUT_DIR = System.getProperty("user.dir")
            +"/src/dataaccess/storage/";

    public static <T> List<T> getObjectFromFile(Class<T> tClass){
        List<T> userList = new ArrayList<>();
        String direc = "";
        try {
            if (tClass == Member.class){
                direc = OUTPUT_DIR+"member.txt";
            }else if (tClass == User.class){
                direc = OUTPUT_DIR+"user.txt";
            }

            //Read from the stored file
            fileInputStream = new FileInputStream(new File(
                    direc));
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
        userList.add(new User("krishna","123"));
        userList.add(new User("sujiv","1234"));
        userList.add(new User("thong","12345"));
        writeObjectToFile(userList,USER_OUTPUT_DIR);

    }


    public static void loadMembers(){
        List<Member> memberList = new ArrayList<>();
        // Store Serialized User Object in File
        memberList.add(new Member("Krishna","KC","123456","1","Librerian",new Address("IOWA","Fairfield","52257","100 N")));
        memberList.add(new Member("Sujiv","Shrestha","123456","2","Librerian",new Address("IOWA","Fairfield","52257","100 N")));
        writeObjectToFile(memberList,OUTPUT_DIR+"member.txt");

    }

    public static void writeObjectToFile(List<? extends Object> serObjList, String directory) {

        try {

            FileOutputStream fileOut = new FileOutputStream(directory);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Object o: serObjList){
                objectOut.writeObject(o);
            }
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
