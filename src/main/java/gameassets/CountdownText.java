package gameassets;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CountdownText extends Text{
    // constructor
    public CountdownText() {
        super();
        super.setMouseTransparent(true);
        super.setFont(Font.font("Impact", 400));
        super.setFill(Color.WHITE);
        // give the text a thick pink border
        super.setStroke(Color.PINK);
        super.setStrokeWidth(10);
        super.setOpacity(1);
    }
}
