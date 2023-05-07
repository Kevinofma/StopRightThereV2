package gameassets;

import javafx.scene.shape.Line;

public class GameLine extends Line {

    // set the width of the line to 5
    public GameLine() {
        super();
        super.setStrokeWidth(7);
        // set the color of the line to white
        super.setStyle("-fx-stroke: grey;");
        // make it mouse transparent so that it doesn't interfere with the buttons
        super.setMouseTransparent(true);
        // round the corners of the line so that the edge of the line is a semi-circle
        super.setSmooth(true);
        // set the opacity of the line to .8
        super.setOpacity(.5);

    }
}
