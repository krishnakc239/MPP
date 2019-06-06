package business.domain;

import java.io.Serializable;

public class User implements Serializable {


    private String userId;
    private String password;
    private String authorizationLevel;
    public static  String userSessionRole = "";

    public User(String userid, String password,String authlevel) {
        this.userId = userid;
        this.password = password;
        this.authorizationLevel = authlevel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthorizationLevel() {
        return authorizationLevel;
    }

    public void setAuthorizationLevel(String authlevel) {
        this.authorizationLevel = authlevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user id :"+ userId +" password : "+ password+ " auth level:"+ authorizationLevel;
    }
}
