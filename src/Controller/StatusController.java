package Controller;

import Backend.DataObject;
import Backend.Similarity;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatusController implements Initializable {

    public GridPane gridPane;
//    public ToggleGroup toggleChoice = new ToggleGroup();
    public RadioButton lineChoice,characterChoice;
    public TextField username;
    public Button check,quit;
    private Similarity compare = new Similarity();
    private String comparison = "data";
    private DataObject dataObj;



    public void line() {
        comparison = "data";
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

        MatrixToGridpane();


    }

    public void MatrixToGridpane(){
        for(int x = 0; x<compare.getMatrix().arraySize(); x++){
            //System.out.print(form.getMatrix().get(0).get(x) + " ");  //to see MatrixToGridpane row values
            for(int y = 0; y<compare.getMatrix().matrixSize(); y++){
                System.out.print(compare.getMatrix().getMatrix().get(y).get(x) + "   ");
                dataObj = new DataObject(compare.getMatrix().getMatrix().get(y).get(x));


                VBox vbox = new VBox();
                vbox.setAlignment(Pos.CENTER);
                vbox.getChildren().addAll(dataObj.getLabel());

                StackPane pane = new StackPane();
                pane.setAlignment(Pos.CENTER);
                pane.getChildren().addAll(dataObj.getRect(),vbox);

                gridPane.add(pane,y,x);
                //gridPane.setGridLinesVisible(true);
            }
            System.out.println();

        }
    }

    public void toExit()
    {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        lineChoice.setToggleGroup(toggleChoice);
//        characterChoice.setToggleGroup(toggleChoice);

    }
}
