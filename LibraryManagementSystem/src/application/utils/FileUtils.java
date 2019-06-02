package application.utils;

import application.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils implements Serializable{

    static FileOutputStream fileOutputStream =null;
    static FileInputStream fileInputStream = null;
    static ObjectInputStream input = null;
    public static final String OUTPUT_DIR = System.getProperty("user.dir")
            +"/src/dataaccess/storage/user.txt";

    public static List<User> getObjectFromFile(){
        List<User> userList = new ArrayList<>();
        try {

            //Read from the stored file
            fileInputStream = new FileInputStream(new File(
                    OUTPUT_DIR));
            input = new ObjectInputStream(fileInputStream);
            try {
                while (fileInputStream.available() > 0) {
                    //Read object from file
                    User user = (User) input.readObject();
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
//            FileOutputStream fileOutputStream1 = new FileOutputStream(OUTPUT_DIR);
        System.out.println("output directory =="+ OUTPUT_DIR);
        User user = new User("krishna","123");
        WriteObjectToFile(user);

    }

    public static void WriteObjectToFile(Object serObj) {

        try {

            FileOutputStream fileOut = new FileOutputStream(OUTPUT_DIR);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
