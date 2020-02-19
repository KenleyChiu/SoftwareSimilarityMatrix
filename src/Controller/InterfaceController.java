package Controller;

import Backend.DataEntry;
import Backend.DataObject;
import Backend.Similarity;
import Backend.SystemMetrics;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InterfaceController implements Initializable {

    public Button compare, exit;
    public AnchorPane matrixAnchor;
    public ListView list;
    public Label vol,length,vocab,longestSimilarString,difficulty,effort,intelligence,time,statusMessage;
    public TableView<DataEntry> similaritiesTable;
    public TableColumn<DataEntry,String> program1,program2,score;
    public CheckBox javaOp,cppOp,allOp,saveAsTextFile;
    public TextField filePath,filesTextField;
    private GridPane gridPane;
    private String comparison = "line",type = "java",folder = "src";
    private boolean onlySourceFiles = true;
    private Similarity similarity = new Similarity();
    private DataObject dataObj;


    //COMPARISONS
    public void line() {
        comparison = "line";
    }
    public void string() {
        comparison = "string";
    }

    //FOLDER
    public void src(){
        folder = "src";
        onlySourceFiles = true;
    }
    public void mergedCodes(){
        folder = "mergedCodes";
        onlySourceFiles = false;
    }

    public void compareFiles() throws IOException {

        boolean makeTextFile = saveAsTextFile.isSelected();

        if(javaOp.isSelected() && !cppOp.isSelected()) type = "java";
        if(cppOp.isSelected() && !javaOp.isSelected()) type = "cpp";
        if(cppOp.isSelected() && javaOp.isSelected()) type = "all";
        if(!cppOp.isSelected() && !javaOp.isSelected()) type = filesTextField.getText();


        similarity.creationMatrix(comparison,type,filePath.getText());

        createMatrix();
        statusMessage.setText("Matrix Created!");

        longestSimilarString.setText(similarity.getSimilarString());


        createTop5();
    }

    private void createTop5(){
        ArrayList<DataEntry> arrayEntry = new ArrayList<>();
        arrayEntry.add(new DataEntry("KENLEY", "MATTHEW", "0.5"));
//
//    ObservableList<DataEntry> entry = FXCollections.observableArrayList(
//            new DataEntry("KENLEY", "MATTHEW", "0.5"),
//            new DataEntry("ee", "ii", "0.3")
//    );

        ObservableList<DataEntry> entry = FXCollections.observableArrayList();
        entry.add(new DataEntry("KENLEY", "MATTHEW", "0.5"));

        program1.setCellValueFactory(new PropertyValueFactory<>("Program1"));
        program2.setCellValueFactory(new PropertyValueFactory<>("Program2"));
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));

        similaritiesTable.setItems(entry);
    }

//
//    public ObservableList<DataEntry> getTop5(){
//        ObservableList<DataEntry> entry = FXCollections.observableArrayList();
//        entry.add(new DataEntry("KENLEY","MATTHEW", "0.5"));
//        entry.add(new DataEntry("ee","ii", "0.3"));
//
//        return entry;
//    }


    public void setMetricsTable() throws IOException {
        SystemMetrics metrics = new SystemMetrics();
        File masterFile;

        if(folder.equals("src")) {
            masterFile = new File("src");  //for our files [Codes or src only]
        } else {
            masterFile = new File("MergedCodes");
        }

        metrics.createSystemMetricsTable(masterFile,onlySourceFiles);  //check this for other's files

        length.setText(Integer.toString(metrics.length()));
        vocab.setText(Integer.toString(metrics.vocab()));
        vol.setText(metrics.volume() + " bits");
        difficulty.setText(Integer.toString(metrics.difficulty()));
        effort.setText(Integer.toString(metrics.effort()));
        time.setText(Integer.toString(metrics.time()));
        intelligence.setText(Integer.toString(metrics.intelligence()));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
