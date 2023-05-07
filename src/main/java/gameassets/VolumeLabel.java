package gameassets;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VolumeLabel extends Text{

    public VolumeLabel() {
        super.setMouseTransparent(true);
        super.setFont(Font.font("Impact", 25));
        // make the text white
        super.setFill(Color.WHITE);
        // make the text have a pink outline
        super.setStyle("-fx-text-fill: white; -fx-stroke: pink; -fx-stroke-width: 2;");
    }
    
}
