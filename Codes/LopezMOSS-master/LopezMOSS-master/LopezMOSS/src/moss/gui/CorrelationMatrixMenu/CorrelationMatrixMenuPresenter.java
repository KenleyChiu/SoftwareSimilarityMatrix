package moss.gui.CorrelationMatrixMenu;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import moss.gui.utilities.CustomColorOperations;
import moss.gui.utilities.CustomFXMLOperations;
import moss.projectpairmachine.ProjectsCorrelationMatrix;
import moss.projectpairmachine.ProjectsCorrelationMatrix.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Presenter for the correlation matrix
 */
public class CorrelationMatrixMenuPresenter {
    @FXML
    private GridPane correlationMatrixTable;
    @Inject
    private CorrelationMatrixMenuModel model;
    @Inject
    private CorrelationMatrixMenuService services;


    /**
     * Loads the matrix in the correlation menu model into the window via a grid pane
     */
    private void loadModelMatrixToTable(){
        final ProjectsCorrelationMatrix matrix = model.getMatrix();
        //PHASE 1: Load headers
        Collection<Text> nameText = new ArrayList<>();
        for (String name : matrix.getProjectNames()){
            nameText.add(new Text(name));
        }
        Text[] nameTextArray = new Text[nameText.size()];
        nameText.toArray(nameTextArray);
        correlationMatrixTable.addRow(0, nameTextArray);


        //PHASE 2: Load all the results from the table into the grid pane
        int row = 1;
        for (ResultRow resultRow : matrix.getRows()){
            Collection<Pane> resultsPaneInRow = new ArrayList<>();
            resultsPaneInRow.add(new StackPane(new Text(resultRow.getProject().getName())));
            for (ResultSet.ResultRecord result : resultRow.getResults()){
                Pane scoreTextContainer = new StackPane(new Text(String.format("%.2f", result.getScore())));
                scoreTextContainer.setBackground(
                        new Background(
                                new BackgroundFill(CustomColorOperations
                                        .interpolateColor(Color.GREEN, Color.RED, result.getScore()), null, null)
                        )
                );
                resultsPaneInRow.add(scoreTextContainer);
            }

            Pane[] resultTextArray = new Pane[resultsPaneInRow.size()];
            resultsPaneInRow.toArray(resultTextArray);
            correlationMatrixTable.addRow(row++, resultsPaneInRow.toArray(resultTextArray));
        }

        correlationMatrixTable.setGridLinesVisible(true);
    }
    @FXML
    private void initialize(){
        loadModelMatrixToTable();
    }

}
