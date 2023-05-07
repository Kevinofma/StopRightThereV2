package gameassets;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PausedHintText extends Text {

    // constructor
    public PausedHintText() {
        super();
        super.setMouseTransparent(true);
        super.setFont(Font.font("Impact", 40));
        super.setFill(Color.WHITE);
        super.setStroke(Color.PINK);
        super.setStrokeWidth(3);
    }
}
