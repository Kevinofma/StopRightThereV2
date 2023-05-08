package gameassets;

import java.util.Random;

import javafx.scene.control.Button;


public class NoteButton extends Button {
    
    // String cssColor = "pink";
    boolean switchColor;
    // constructor
    public NoteButton() {
        super();
        super.setPrefSize(100, 100);
        // change the font of the button to Impact, bold, 28pt
        super.setFont(javafx.scene.text.Font.font("Impact", javafx.scene.text.FontWeight.BOLD, 28));
    }

    public static String getRandomCSSColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return String.format("#%02x%02x%02x", r, g, b);
    }


}