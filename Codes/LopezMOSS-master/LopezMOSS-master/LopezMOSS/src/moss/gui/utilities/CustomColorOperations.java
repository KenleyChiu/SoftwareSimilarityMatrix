package moss.gui.utilities;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * A custom set of operations for working with colors in Java
 */
public final class CustomColorOperations {
    private CustomColorOperations(){}

    /**
     * Used to find a color at a particular percentage between a start and end color.
     * For example, you may use this to find a color 60% between red an dblue
     * @param start Starting color
     * @param end Ending color
     * @param percentage Percentage between the two colors of the output color
     * @return Color at the <b>percentage</b> * 100% of start and end
     */
    public static Paint interpolateColor(Color start, Color end, double percentage){
        return start.interpolate(end, percentage);
    }

}
