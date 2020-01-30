package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StatusController {

    public Button quit,again;
    public Label status,longestSame;


    public void passValues(String message) {
        this.status.setText("Matrix created per " + message + "!");
    }

}
