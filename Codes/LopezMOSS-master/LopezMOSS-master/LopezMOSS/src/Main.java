import com.airhacks.afterburner.views.FXMLView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import moss.algorithm.ComparisonStrategy;
import moss.algorithm.TokenHashingStrategy;
import moss.gui.MultiProjectMenu.MultiProjectMenuView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MultiProjectMenuView startingPoint = new MultiProjectMenuView();
        Scene startingScene = new Scene(startingPoint.getView());
        primaryStage.setScene(startingScene);
        primaryStage.show();

    }
}
