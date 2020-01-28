package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UsernameController {

    public Button enter;
    public TextField nameField;
    private Stage stage;

    public void toNext() throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("../FXML/Filenames.fxml"));
        Parent root = (Parent) fxmloader.load();

        Scene scene = new Scene(root);

        FilenamesController passCont = fxmloader.getController();
        passCont.getFileName(nameField.getText());
        passCont.passStage(stage);

        stage.setScene(scene);

        int x = 0;

        stage.show();
    }

    public void passStage(Stage stage){
        this.stage = stage;
    }

    public void whatsoever()
    {
        System.out.println("HELLO Universe");
        System.out.println("Hello");
    }

}


