package application.controller;

import application.domain.User;
import application.utils.FileUtils;
import application.utils.ViewUtils;
import javafx.event.ActionEvent;
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
            System.out.println("inside login !!!!!!!!!!!!!!!!");
            String uname = user_name.getText();
            String pword = user_pasword.getText();
            List<User> userList = FileUtils.getObjectFromFile();
            boolean check = false;
            for (User user: userList) {
                if (uname.equals(user.getUserName()) && pword.equals(user.getPassword())){
                    System.out.println("Login Success :"+ user.getUserName());
                    err_level.setText("Login Success :" + user.getUserName());
                    check = true;
                    Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                    root = new ViewUtils().getRoot("../../ui/dashboard.fxml");
                    Scene scene = new Scene(root);
                    window.setTitle("DASHBOARD");
                    window.setScene(scene);
                    window.show();
                    break;
                }
            }
            if (!check) {
                err_level.setText("Login Failed");
            }

        });
    }
}
