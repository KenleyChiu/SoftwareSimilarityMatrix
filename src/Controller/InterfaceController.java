package Controller;

import Backend.DataObject;
import Backend.Similarity;
import Backend.SystemMetrics;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class InterfaceController {

    public Button compare, exit;
    public GridPane gridPane;
    public AnchorPane matrixAnchor;
    public ListView list;
    public VBox vboxMain;
    public Label vol;
    private String comparison = "line",type = "java";
    private Similarity similarity = new Similarity();
    private DataObject dataObj;


    //COMPARISONS
    public void line() {
        comparison = "line";
    }
    public void character() {
        comparison = "character";
    }

    //FILE TYPES

    public void java() {
        type = "java";
    }
    public void cpp() {
        type = "cpp";
    }
    public void all() {
        type = "all";
    }


    public void compareFiles() throws IOException {

        similarity.creationMatrix(comparison,type);

//        list.setItems();
        createMatrix();

        SystemMetrics metrics = new SystemMetrics();

        File masterFile = new File("src");  //for our files [Codes or src only]
        metrics.createSystemMetricsTable(masterFile);  //check this for other's files

        vol.setText(Integer.toString(metrics.volume()));
    }

    public void createMatrix() {

        addRowsColumns();

        MatrixToGridpane();


    }

    public void addRowsColumns(){

        removeRowsColumns();

        gridPane = new GridPane();
        gridPane.setVgap(-1);
//        gridPane.setHgap(1);
        gridPane.setGridLinesVisible(true);
        matrixAnchor.getChildren().add(gridPane);
//        matrixAnchor.setSpacing(10);

        for(int x=0; x<similarity.getMatrix().arraySize()+1; x++){
            gridPane.addRow(x);
            for(int y=0; y<similarity.getMatrix().matrixSize()+1; y++){
                gridPane.addColumn(y);
            }
        }

    }

    public void removeRowsColumns(){
        matrixAnchor.getChildren().remove(gridPane);
    }

    public void MatrixToGridpane(){
        System.out.println("MATRIX BY "+comparison.toUpperCase()+":");
//        matrixAnchor.setPrefSize(1400,850);
        gridPane.setAlignment(Pos.CENTER);
        int z=1;
        VBox names;
        Label userFile;

        Label a = new Label("");

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(a);

        gridPane.add(vb,0,0);
        for(int v=0;v<similarity.getMatrix().matrixSize();v++){
//            System.out.print(similarity.getMatrix().getUserFileNames().get(v));
            //FOR LABELS
            userFile = new Label(similarity.getMatrix().getUserFileNames().get(v));
            System.out.print(similarity.getMatrix().getUserFileNames().get(v));

            names = new VBox();
            names.setAlignment(Pos.CENTER);
            names.getChildren().addAll(userFile);

            gridPane.add(names,v+1,0);
        }
        System.out.println();

        for(int x=0; x<similarity.getMatrix().arraySize(); x++){
            //System.out.print(form.getMatrix().get(0).get(x) + " ");  //to see MatrixToGridpane row values
            userFile = new Label(similarity.getMatrix().getUserFileNames().get(x));

            names = new VBox();
            names.setAlignment(Pos.CENTER);
            names.getChildren().addAll(userFile);

            gridPane.add(names,0,z);
            z++;


            for(int y=0; y<similarity.getMatrix().matrixSize(); y++){
                System.out.print(" " + similarity.getMatrix().getMatrix().get(y).get(x) + "  ");

                dataObj = new DataObject(similarity.getMatrix().getMatrix().get(y).get(x),userFile);

                //FOR SCORES
                VBox vbox = new VBox();
                vbox.setAlignment(Pos.CENTER);
                vbox.getChildren().addAll(dataObj.getLabel());

                StackPane pane = new StackPane();
                pane.setAlignment(Pos.CENTER);
                pane.getChildren().addAll(dataObj.getRect(),vbox);

                gridPane.add(pane,y+1,x+1);

            }
            System.out.println();
        }
    }

    public void exitProg() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

}
