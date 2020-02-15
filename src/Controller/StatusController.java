package Controller;

import Backend.*;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatusController implements Initializable {

    public VBox vboxMain;
    public RadioButton lineChoice,characterChoice;
    public TextField username;
    public Button check,quit;
    public GridPane gridPane;
    private Similarity compare = new Similarity();
    private String comparison = "line",type="";
    private DataObject dataObj;



    public void line() {
        comparison = "line";
    }

    public void character() {
        comparison = "character";
    }

    public void createMatrix() throws IOException {

        compare.creationMatrix(comparison,type);

        addRowsColumns();

        MatrixToGridpane();


    }

    public void addRowsColumns(){

        removeRowsColumns();

        gridPane = new GridPane();
        gridPane.setVgap(-2);
        gridPane.setGridLinesVisible(true);
        vboxMain.getChildren().add(gridPane);
        vboxMain.setSpacing(10);

        for(int x=0; x<compare.getMatrix().arraySize(); x++){
            gridPane.addRow(x);
            for(int y=0; y<compare.getMatrix().matrixSize(); y++){
                gridPane.addColumn(y);
            }
        }

    }

    public void removeRowsColumns(){
        vboxMain.getChildren().remove(gridPane);
    }

    public void MatrixToGridpane(){
        System.out.println("MATRIX BY "+comparison.toUpperCase()+":");
//        vboxMain.setPrefSize(1600,850);
//        gridPane.setAlignment(Pos.CENTER);
        for(int x=0; x<compare.getMatrix().arraySize(); x++){
            //System.out.print(form.getMatrix().get(0).get(x) + " ");  //to see MatrixToGridpane row values
            for(int y=0; y<compare.getMatrix().matrixSize(); y++){
                System.out.print(compare.getMatrix().getMatrix().get(y).get(x) + "  ");

                //FOR LABELS
                Label userFile = new Label(compare.getMatrix().getUserFileNames().get(y));
                System.out.println(compare.getMatrix().getUserFileNames().get(y));

                dataObj = new DataObject(compare.getMatrix().getMatrix().get(y).get(x),userFile);

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

    }
}
