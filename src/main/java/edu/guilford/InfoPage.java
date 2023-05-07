package edu.guilford;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InfoPage extends Pane{

    private Button scene2Button;

    //constructor
    public InfoPage() {
        scene2Button = new Button("Click to start2");
        // add the button to the pane
        this.getChildren().add(scene2Button);
        scene2Button.setOnAction(event -> {
            // Create a new instance of the Scene class and set it as the active scene
            Scene newScene = new Scene(new StartMenuPane(), 400, 500);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(newScene);
        });

    }
}
