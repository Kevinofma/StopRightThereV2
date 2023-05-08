package menuassets;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DifficultyText extends Text {
    
    // Constructor
    public DifficultyText(String text) {
        super(text);
        super.setFont(javafx.scene.text.Font.font("Impact", javafx.scene.text.FontWeight.BOLD, 100));
        super.setFill(javafx.scene.paint.Color.WHITE);
        super.setOpacity(1);
        super.setStroke(Color.WHITE);
        super.setStrokeWidth(5);
        


    }
}
