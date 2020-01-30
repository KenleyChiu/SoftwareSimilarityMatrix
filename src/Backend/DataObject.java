package Backend;


import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class DataObject {

    private Rectangle rect = new Rectangle();
    private float data;
    private VBox vbox = new VBox();

    public DataObject(float data){
        this.data = data;

    }

}
