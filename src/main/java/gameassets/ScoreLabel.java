package gameassets;

import javafx.scene.control.Label;

public class ScoreLabel extends Label{

    // constructor
    public ScoreLabel() {
        super();
        super.setStyle("-fx-font-size: 22px; -fx-text-fill: white; -fx-font-family: Impact;");
        super.setOpacity(0.8);
    }

    // create a setText method that takes in a string and sets the text of the label to that string
    public void setScore(int score) {
        super.setText("Score: " + score);
    }
}
