package business.controller;

import business.domain.Role;
import business.domain.User;
import business.utils.FileUtils;
import business.utils.ViewUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoginController {

    public TextField user_name;
    public PasswordField user_password;
    public Button lgn;
    public Label err_lvl;

    public void initialize(){
        lgn.setOnAction(event -> {
            String uname = user_name.getText();
            String pword = user_password.getText();
            List<User> userList = FileUtils.getObjectFromFile(User.class);
            boolean check = false;
            for (User user: userList) {
                if (uname.equals(user.getUserId()) && pword.equals(user.getPassword())){
                    System.out.println("Login Success :"+ user.getUserId());
                    check = true;
                    User.userSessionRole = user.getAuthorizationLevel(); //set userid for session
                    String authorizationLevel = user.getAuthorizationLevel();
                    if(SystemController.authorize(authorizationLevel)){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../ui/dashboard.fxml"));
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        Parent root = new ViewUtils().getRoot("../../ui/dashboard.fxml");
                        Stage currentStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        currentStage.setScene(scene);
                        SystemController systemController = loader.getController();
                        if (User.userSessionRole.equals(Role.ADMIN)){
                            systemController.hideCheckoutButton();
                        }
                    }
                    break;
                }
            }
            if (!check) {
                err_lvl.setText("Login Failed");
            }

        });
    }
}
