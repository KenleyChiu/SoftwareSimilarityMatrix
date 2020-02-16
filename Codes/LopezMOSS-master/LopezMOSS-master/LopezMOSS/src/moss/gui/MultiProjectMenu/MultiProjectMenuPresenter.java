package moss.gui.MultiProjectMenu;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import moss.gui.CorrelationMatrixMenu.CorrelationMatrixMenuModel;
import moss.gui.CorrelationMatrixMenu.CorrelationMatrixMenuView;
import moss.gui.utilities.CustomFXMLOperations;
import moss.project.PathFilter;

import javax.inject.Inject;
import java.nio.file.Path;

/**
 * Controls the multi-project menu
 */
public class MultiProjectMenuPresenter {
    //MENU ITEMS: SEPARATE ITEM MENU
    @FXML
    private ListView<Path> includedProjectsListView;
    @FXML
    private Button addProjectButton;
    @FXML
    private Button compareSeparateButton;

    //FILTER ITEMS
    @FXML
    private RadioButton regexRadio;
    @FXML
    private RadioButton glubRadio;
    @FXML
    private TextField filterText;
    @FXML
    private ToggleGroup filterType;
    @FXML
    private ListView<String> filterListView;

    //MULTI-PROJECT FOLDER COMPARISON
    @FXML
    private Button startComparisonButton;
    @FXML
    private Label projectCountLabel;
    @FXML
    private Label projectPathLabel;
    @FXML
    private Button projectPathChooseButton;



    //RENAME: renamed from Controller to Presenter to avoid confusion while reading papers on FXML conventions
    @Inject
    private MultiProjectMenuService services;
    @Inject
    private MultiProjectMenuModel singlePathProjectsModel;
    @Inject
    private SeparateProjectMenuModel separateProjectMenuModel;
    @Inject
    private CorrelationMatrixMenuModel correlationMatrixModel;


    @FXML
    private void initialize(){
        //PART 1: Project path selection
        projectPathChooseButton.setOnMouseClicked((event -> {
            //PROJECT PATH CHOOSE BUTTON ACTION
            Path requestedPath = services.requestDirectoryFromUser();
            singlePathProjectsModel.setChosenProjectsPath(requestedPath);
            projectPathLabel.setText(String.valueOf(requestedPath));
            projectCountLabel.setText(String.valueOf(services.folderCount(requestedPath)));
            startComparisonButton.setDisable(false);
        }));

        //PART 2: Text filter
        filterText.setOnAction((event -> {
            filterListView.getItems().add(filterText.getText());
            singlePathProjectsModel.getFilterBuilder().addFilter(filterText.getText());
        }));
        //VERBOSE: This simply takes the underlying value of the newly selected radio button (Filter type) and
        //injects that to the filter builder
        filterType.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
                singlePathProjectsModel.getFilterBuilder().setFilterType((PathFilter.Type) newValue.getUserData()));
        regexRadio.setUserData(PathFilter.Type.REGEX);
        glubRadio.setUserData(PathFilter.Type.GLOB);


        //PART 3: Comparison button
        startComparisonButton.setDisable(true);
        startComparisonButton.setOnMouseClicked((event -> {
            //creates the correlation matrix and loads it into the correlation matrix menu's singlePathProjectsModel
            correlationMatrixModel.loadMatrix(services.processMultiProjectFolder(singlePathProjectsModel.getChosenProjectsPath(), singlePathProjectsModel.createFilter()));

            //Load correlation matrix menu
            CustomFXMLOperations.showFxmlViewInWindow(CorrelationMatrixMenuView.class);
        }));




        //PART 4: ADD PROJECT BUTTON
        addProjectButton.setOnMouseClicked(event ->{
            Path pathToBeAdded = services.requestDirectoryFromUser();
            separateProjectMenuModel.addProject(pathToBeAdded);
            includedProjectsListView.getItems().add(pathToBeAdded);
        });

        //PART 5: Compare separate projects button
        compareSeparateButton.setOnMouseClicked(event -> {
            correlationMatrixModel.loadMatrix(services.processMultiPaths(
                            separateProjectMenuModel.retrieveProjectPaths(), singlePathProjectsModel.createFilter()));
            CustomFXMLOperations.showFxmlViewInWindow(CorrelationMatrixMenuView.class);
                }
        );


    }
}
