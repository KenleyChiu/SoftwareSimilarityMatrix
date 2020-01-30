package Controller;

import Backend.Similarity;
import com.sun.org.apache.xpath.internal.operations.String;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StatusController {

    public Label matrixStatus,matrixText;

    public void passMatrix(StringBuilder message,int i) {
        if(i == 0) this.matrixStatus.setText("Matrix Created - Compared by line");
        else this.matrixStatus.setText("Matrix Created - Compared by character");

        StringBuilder text = new StringBuilder(message);
        this.matrixText.setText(text.toString());
    }

}
