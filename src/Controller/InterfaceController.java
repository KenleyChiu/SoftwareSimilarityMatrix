package Controller;

import Backend.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import metrics.HalsteadMetrics;
import metrics.MetricMachine;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InterfaceController implements Initializable {

    public Button compare, exit;
    public AnchorPane matrixAnchor;
    public Label vol,length,vocab,longestSimilarString,difficulty,effort,intelligence,time,statusMessage;
    public Label apiLength,apiVocab,apiDifficulty,apiEffort,apiTime,apiVolume,apiBugs,statusMessage2;
//    public TableView<DataEntry> similaritiesTable;
//    public TableColumn<DataEntry,String> program1,program2,score;
    public ListView<String> top10Listview;
    public CheckBox javaOp,cppOp,othersOp1,saveAsTextFile;
    public TextField filePath,filesTextField,logFileName,folderTextfield;
    private GridPane gridPane;
    private String comparison = "line",folder;
    private ArrayList<String> type; //Kenley Edit
    private boolean onlySourceFiles = true;
    private Similarity similarity = new Similarity();
    private DataObject dataObj;
    private FileHandling files= new FileHandling();


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
//    public void mergedCodes(){
//        folder = "mergedCodes";
//        onlySourceFiles = false;
//    }

    public void otherOperation()
    {
        if(othersOp1.isSelected()) filesTextField.setDisable(false);
        else filesTextField.setDisable(true);
    }

    public void textFile(){
        if(saveAsTextFile.isSelected()) logFileName.setDisable(false);
        else logFileName.setDisable(true);
    }

    public void retrieveGit() throws IOException {
        files.gitFileRetriever();
    }

    public void compareFiles() throws IOException {

//        statusMessage.setText("Loading");

        //Kenley Edit
        type= new ArrayList<>();
        if(javaOp.isSelected()) type.add(".java");
        if(cppOp.isSelected()) type.add(".cpp");
        if(othersOp1.isSelected())
        {
            if (filesTextField.getText() == null || filesTextField.getText().trim().isEmpty()) {                statusMessage.setText("Textfield is Empty!");
                statusMessage.setText("Others Textfield is Empty!");
                return;
//                throw new NullPointerException("Invalid Input");
            }
            else
                type.add(filesTextField.getText());

        }
        if(saveAsTextFile.isSelected())
        {
            if (logFileName.getText() == null || logFileName.getText().trim().isEmpty()) {
                statusMessage.setText("Enter a Text File Name!");
                return;
//                throw new NullPointerException("Invalid Input");
            }
            else
                type.add(filesTextField.getText());

        }

        filesTextField.clear();
        //Kenley Edit

        similarity.creationMatrix(comparison,type,filePath.getText());

        createMatrix();

        statusMessage.setText("Matrix Created!");
        longestSimilarString.setText(similarity.longestString());

        if(saveAsTextFile.isSelected()) files.writeScoreFile(similarity.getMatrix(),logFileName.getText());

        setTop10Listview();

    }

    private void setTop10Listview(){
        for(int i=0; i<similarity.getMatrix().getResultsSize(); i++)
        {
            top10Listview.getItems().add(similarity.getMatrix().getRowRepo(i)+ " and " + similarity.getMatrix().getColumnRepo(i)+": " + similarity.getMatrix().getResults(i));
        }
    }

    public void setMetricsTableApi(String pathDirectory) throws IOException {
        HalsteadMetrics hal = MetricMachine.getMetrics(pathDirectory);

        apiLength.setText(Double.toString(Math.round(hal.getProglen())));
        apiVocab.setText(Double.toString(Math.round(hal.getVocabulary())));
        apiVolume.setText(Double.toString(Math.round(hal.getVolume())) + " bits");
        apiDifficulty.setText(Double.toString(Math.round(hal.getDifficulty())));
        apiEffort.setText(Double.toString(Math.round(hal.getEffort())));
        apiTime.setText(Double.toString(Math.round(hal.getTimeDelBugs())));
        apiBugs.setText(Double.toString(Math.round(hal.getTimeDelBugs())));
    }

    public void setMetricsTable() throws IOException {
        SystemMetrics metrics = new SystemMetrics();
        File masterFile;

        if(folderTextfield.getText() == null || folderTextfield.getText().trim().isEmpty()) {
            statusMessage2.setText("Enter a Folder File Path!");
            return;
        }
        else {
            folder = folderTextfield.getText().replaceAll("\\\\", "\\\\\\\\");
            masterFile = new File(folder);  //enter src for our files [API only works for java files
        }

        metrics.createSystemMetricsTable(masterFile,onlySourceFiles);  //check this for other's files

        length.setText(Integer.toString(metrics.length()));
        vocab.setText(Integer.toString(metrics.vocab()));
        vol.setText(metrics.volume() + " bits");
        difficulty.setText(Integer.toString(metrics.difficulty()));
        effort.setText(Integer.toString(metrics.effort()));
        time.setText(Integer.toString(metrics.time()));
        intelligence.setText(Integer.toString(metrics.intelligence()));

        //API METRICS
        setMetricsTableApi(folder);
    }

    public void createMatrix() {
        addRowsColumns();
        MatrixToGridpane();

        matrixAnchor.setPrefSize(gridPane.getMaxHeight(),gridPane.getMaxHeight());
    }


    public void addRowsColumns(){

        removeRowsColumns();

        gridPane = new GridPane();
//        gridPane.setVgap(1);
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

                //FOR SCORES
                VBox vbox = new VBox();
                vbox.setAlignment(Pos.CENTER);

                dataObj = new DataObject(similarity.getMatrix().getMatrix().get(y).get(x),userFile,vbox);

                dataObj.getVbox().getChildren().addAll(dataObj.getLabel());

                StackPane pane = new StackPane();
                pane.setAlignment(Pos.CENTER);
                pane.getChildren().addAll(dataObj.getRect(),vbox);

                gridPane.add(dataObj.getVbox(),y+1,x+1);

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
