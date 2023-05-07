package gameassets;

import javafx.scene.control.Label;

public class AccuracyLabel extends Label{

    // constructor
    public AccuracyLabel() {
        super();
        super.setStyle("-fx-font-size: 22px; -fx-text-fill: white; -fx-font-family: Impact;");
        super.setOpacity(0.8);
    }

    public void setAccuracy(double accuracy) {
        super.setText("Accuracy: " + accuracy + "%");
    }
}
