package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PercentageController {

    public Button quit,again;
    public Label percentage,longestSame;


    public void passValues(String message) {
        this.percentage.setText(message);
    }

}
