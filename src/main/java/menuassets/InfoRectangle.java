package menuassets;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class InfoRectangle extends Rectangle {
    
    // constructor
    public InfoRectangle() {
        super();
    }

    public void setGradColor(Color color) {
        Stop[] stops = new Stop[] { new Stop(0.25, color), new Stop(1, Color.WHITE)};
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, stops);
        super.setFill(lg1);
    }
}
