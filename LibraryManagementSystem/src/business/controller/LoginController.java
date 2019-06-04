package business.controller;

import business.domain.User;
import business.utils.FileUtils;
import business.utils.ViewUtils;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class LoginController {

    public BorderPane borderPane;
    public Parent root;
    public TextField user_name;
    public PasswordField user_pasword;
    public Button lgn;
    public Label err_level;

    public void initialize(){
        lgn.setOnAction(event -> {
            String uname = user_name.getText();
            String pword = user_pasword.getText();
            List<User> userList = FileUtils.getObjectFromFile(User.class);
            boolean check = false;
            for (User user: userList) {
                if (uname.equals(user.getUserId()) && pword.equals(user.getPassword())){
                    System.out.println("Login Success :"+ user.getUserId());
                    check = true;
                    String authorizationLevel = user.getAuthorizationLevel();
                    if(SystemController.authorize(authorizationLevel)){
                        Parent root = new ViewUtils().getRoot("../../ui/dashboard.fxml");
                        Stage currentStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        currentStage.setScene(scene);
                    }
                    break;
                }
            }
            if (!check) {
                err_level.setText("Login Failed");
            }

        });
    }
}
