package TutorialSlideShow;

import edu.guilford.LevelSelect;
import edu.guilford.StartMenuPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Tutorial extends Pane{

    private Button scene2Button;

    //constructor
    public Tutorial() {
        scene2Button = new Button("Click to start2");
        // add the button to the pane
        this.getChildren().add(scene2Button);
        scene2Button.setOnAction(event -> {
            StartMenuPane.mainMenuPlayer.stop();
            LevelSelect.setBeatsToDelayOffset(-1.5);
            Scene mainMenuScene = new Scene(new StartMenuPane(), 1250, 650);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(mainMenuScene);
        });

    }
}
