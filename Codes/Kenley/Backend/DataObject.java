package Backend;


import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class DataObject {

    private Rectangle rect;
    private float data;
    private Label dataLabel;

    public DataObject(float data) {
        this.data = data;

        rect = new Rectangle();

        rect.setWidth(45);
        rect.setHeight(25);

        if (data == 1.0) rect.setFill(Color.hsb(330, 1, 1));
        if (0.90 <= data && data < 1.0) rect.setFill(Color.hsb(300, 1, 1));
        if (0.80 <= data && data < 0.90) rect.setFill(Color.hsb(270, 1, 1));
        if (0.70 <= data && data < 0.80) rect.setFill(Color.hsb(240, 1, 1));
        if (0.60 <= data && data < 0.70) rect.setFill(Color.hsb(210, 1, 1));
        if (0.50 <= data && data < 0.60) rect.setFill(Color.hsb(180, 1, 1));
        if (0.40 <= data && data < 0.50) rect.setFill(Color.hsb(150, 1, 1));
        if (0.30 <= data && data < 0.40) rect.setFill(Color.hsb(120, 1, 1));
        if (0.20 <= data && data < 0.30) rect.setFill(Color.hsb(90, 1, 1));
        if (0.10 <= data && data < 0.20) rect.setFill(Color.hsb(60, 1, 1));
        if (0.0 < data && data < 0.10) rect.setFill(Color.hsb(30, 1, 1));
        if (0.0 == data) rect.setFill(Color.hsb(0, 1, 1));


        dataLabel = new Label(Float.toString(data));

    }

    public Label getLabel() {
        return dataLabel;
    }

    public float getData() {
        return data;
    }

    public Rectangle getRect() {
        return rect;
    }
}