package menuassets;

import javafx.scene.control.Button;

public class ArrowButton extends Button{
    
    // Constructor
    public ArrowButton(String text) {
        super(text);
        // back the background invisible
        super.setStyle("-fx-background-color: transparent;");
        super.setOpacity(1);
        super.setFont(javafx.scene.text.Font.font("Impact", javafx.scene.text.FontWeight.BOLD, 60));
        super.setTextFill(javafx.scene.paint.Color.WHITE);
    }
}
