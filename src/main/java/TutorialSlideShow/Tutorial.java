package TutorialSlideShow;

import edu.guilford.LevelSelect;
import edu.guilford.StartMenuPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menuassets.MenuButton;

public class Tutorial extends Pane{

    private MenuButton mainMenuButton;

    //constructor
    public Tutorial() {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        // set the background to the tutorialSlide1.jpg
        this.setStyle("-fx-background-image: url('tutorialBasic.PNG'); -fx-background-size: 1000, 650;");
        mainMenuButton = new MenuButton();
        mainMenuButton.setText("Return To Main Menu");
        mainMenuButton.setLayoutX(550);
        mainMenuButton.setLayoutY(600);
        this.getChildren().add(mainMenuButton);

        mainMenuButton.setOnAction(event -> {
            StartMenuPane.mainMenuPlayer.stop();
            LevelSelect.setBeatsToDelayOffset(-1.5);
            Scene mainMenuScene = new Scene(new StartMenuPane(), 1250, 650);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(mainMenuScene);
        });

    }
}
