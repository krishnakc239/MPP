package business.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ViewUtils {
    public  Parent getRoot(String fxmlUrl){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}
