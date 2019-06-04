package business.utils;

import business.domain.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils implements Serializable{

    static FileOutputStream fileOutputStream =null;
    static FileInputStream fileInputStream = null;
    static ObjectInputStream input = null;
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
        List<LibraryMember> memberList = new ArrayList<>();
        // Store Serialized User Object in File
        memberList.add(new LibraryMember("1111",new Person("Krishna","KC","12345"),new Address("IOWA","Fairfield","52257","100 N")));
        memberList.add(new LibraryMember("2222",new Person("Sujiv","Shrestha","45678"),new Address("DAKOTA","Fairfield","52257","100 N")));
        memberList.add(new LibraryMember("3333",new Person("Thong","Huang","7890"),new Address("TEXAS","Fairfield","52257","100 N")));
        writeObjectToFile(memberList);

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
}