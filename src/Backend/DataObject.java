package Backend;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class DataObject {

    private Rectangle rect;
    private float data;
    private Label dataLabel;

    public DataObject(float data){
        this.data = data;

        rect = new Rectangle();
        rect.setFill(Color.YELLOW);

        dataLabel = new Label(Float.toString(data));

    }

    public Label getLabel(){
        return dataLabel;
    }

    public float getData(){
        return data;
    }

    public Rectangle getRect(){
        return rect;
    }

}
