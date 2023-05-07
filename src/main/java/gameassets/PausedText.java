package gameassets;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PausedText extends Text {

    // constructor
    public PausedText() {
        super();
        super.setMouseTransparent(true);
        super.setFont(Font.font("Impact", 150));
        super.setFill(Color.WHITE);
        super.setStroke(Color.PINK);
        super.setStrokeWidth(3);
    }
}
