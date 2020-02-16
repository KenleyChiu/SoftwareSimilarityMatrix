package Controller;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage stage = primaryStage;

        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("../FXML/Status.fxml"));
        Parent root =fxmloader.load();

        primaryStage.setTitle("Software Similarity Program");
        primaryStage.setScene(new Scene(root, 1600, 850));

//        UsernameController passCont = fxmloader.getController();
//        passCont.passStage(stage);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
