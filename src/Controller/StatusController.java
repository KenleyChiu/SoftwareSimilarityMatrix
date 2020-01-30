package Controller;

import Backend.Similarity;
import com.sun.org.apache.xml.internal.security.Init;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatusController implements Initializable {

//    public Label matrixStatus,matrixText;
    public GridPane gridPane;
    public ToggleGroup toggleChoice = new ToggleGroup();
    public RadioButton lineChoice,characterChoice;
    public TextField username;
    public Button check,quit;
    private Similarity compare = new Similarity();
    private String comparison = "";


//    public void passMatrix(StringBuilder message,int i) {
//        if(i == 0) this.matrixStatus.setText("Matrix Created - Compared by line");
//        else this.matrixStatus.setText("Matrix Created - Compared by character");
//
//        StringBuilder text = new StringBuilder(message);
//        this.matrixText.setText(text.toString());
//    }


    public void line() {
        comparison = "line";
    }

    public void character() {
        comparison = "character";
    }

    public void createMatrix() throws FileNotFoundException {


//        if(lineChoice.isSelected()) line();
//        else {
//            character();
//            type = 1;
//        }

        compare.readFile(comparison);


    }

    public void toExit()
    {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lineChoice.setToggleGroup(toggleChoice);
        characterChoice.setToggleGroup(toggleChoice);


    }
}
