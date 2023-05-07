package gameassets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TimeRemainingBar extends Rectangle{

    // constructor
    public TimeRemainingBar() {
        super();
        super.setFill(Color.WHITE);
        // round the corners of the rectangle
        super.setArcWidth(20);
        super.setArcHeight(20);
    }
}
