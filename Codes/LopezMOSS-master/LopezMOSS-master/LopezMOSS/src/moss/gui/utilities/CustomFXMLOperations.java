package moss.gui.utilities;

import com.airhacks.afterburner.views.FXMLView;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contains utility operation methods for FXML view communication and processing
 */
public final class CustomFXMLOperations {
    //This class cannot be constructed from the outside because it is only meant as a container for basic GUI operations
    private CustomFXMLOperations(){ /*NOTE: this class is not instantiable*/ }

    /**
     * @param viewClass Class instance of the FXML view to be loaded unto the screen
     */
    public static void showFxmlViewInWindow(Class<? extends FXMLView> viewClass){
        try {
            FXMLView view = viewClass.newInstance();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(view.getView()));
            newStage.show();

        } catch (InstantiationException | IllegalAccessException e) {
            //these exceptions have been blocked because they give no additional information to the outside user.
            //furthermore, the afterburner library, where the FXMLView class was taken from, is known to be reliable
            e.printStackTrace();
        }
    }
}
