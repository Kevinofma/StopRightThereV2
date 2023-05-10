package menuassets;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuButton extends Button{

    // constructor
    public MenuButton() {
        super();
        super.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.9), 10, 0, 0, 0);");
        super.setTextFill(Color.rgb(255, 255, 255));
        super.setBackground(new Background(
                new BackgroundFill(Color.DEEPPINK, new CornerRadii(10), Insets.EMPTY)));
                super.setBorder(new Border(
            new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10),
            new BorderWidths(3)))); // Set border width to 3 pixels
            super.setFont(Font.font("Impact", 14));
    }
}
