package menuassets;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HardLvlButton extends Button{
    
    // Constructor
    public HardLvlButton(String text) {
        super(text);
        super.setFont(Font.font("Impact", FontWeight.BOLD, 40));
        super.setTextFill(Color.WHITE);
        BackgroundFill fill = new BackgroundFill(Color.rgb(250, 0, 0, 0.5), new CornerRadii(5), Insets.EMPTY);
        super.setBackground(new Background(fill));
        super.setOpacity(1);
    }
}
