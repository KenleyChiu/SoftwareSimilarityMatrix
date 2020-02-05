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
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StatusController implements Initializable {

    public GridPane gridPane;
//    public ToggleGroup toggleChoice = new ToggleGroup();
    public RadioButton lineChoice,characterChoice;
    public TextField username;
    public Button check,quit;
    private Similarity compare = new Similarity();
    private String comparison = "line";
    private DataObject dataObj;
    private Boolean status = true;
    private ArrayList<DataObject> objArray;
    private ArrayList<ArrayList<DataObject>> objMatrix = new ArrayList<>();



    public void line() {
        comparison = "line";
    }

    public void character() {
        comparison = "character";
    }

    public void createMatrix() throws FileNotFoundException {



        if(!status){
//            gridPane.getChildren().removeAll(); //not working

//            for(int x=0;x<35;x++){
//                for(int y=0;y<35;y++){
//                    gridPane.getChildren().remove(objMatrix.get(x).get(y).getRect());
//                    System.out.println(objArray.get(0).getData());
//                }
//            }

        }

//        if(lineChoice.isSelected()) line();
//        else {
//            character();
//            type = 1;
//        }

        compare.readFile(comparison,status);

        objArray = new ArrayList<>();

        if(status) first();
        else second();



    }

    public void first(){
        for(int x=0;x<compare.getMatrix1().arraySize();x++){
            //System.out.print(form.getMatrix().get(0).get(x) + " ");  //to see first row values
            for(int y=0;y<compare.getMatrix1().matrixSize();y++){
                System.out.print(compare.getMatrix1().getMatrix().get(y).get(x) + "   ");
                dataObj = new DataObject(compare.getMatrix1().getMatrix().get(y).get(x));

                //objArray.add(dataObj);

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

            //objMatrix.add(objArray);
            status = false;
        }
    }

    public void second(){
        for(int x=0;x<compare.getMatrix2().arraySize();x++){
            //System.out.print(form.getMatrix().get(0).get(x) + " ");  //to see first row values
            for(int y=0;y<compare.getMatrix2().matrixSize();y++){
                System.out.print(compare.getMatrix2().getMatrix().get(y).get(x) + "   ");
                dataObj = new DataObject(compare.getMatrix2().getMatrix().get(y).get(x));

                //objArray.add(dataObj);

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

            //objMatrix.add(objArray);
            status = false;
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
