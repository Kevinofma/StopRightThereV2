package edu.guilford;

import java.nio.file.Paths;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class StartMenuPane extends Pane {

    private Button startButton;
    private Button infoButton;
    MediaPlayer mediaPlayer;

    //constructor
    public StartMenuPane() {

        startButton = new Button("Start");
        infoButton = new Button("Information");

        // add the start button to the pane
        this.getChildren().add(startButton);
        startButton.setLayoutX(122);
        startButton.setLayoutY(230);
        // set the style of the button to a font of 12 Dialog, bold, remove the focus color, set the border color to black, set the border width to 1px, set the background radius to 2, 2, 2, 2
        startButton.setStyle("-fx-font: 12 \"Dialog\"; -fx-font-weight: bold; -fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; -fx-background-radius: 2, 2, 2, 2;");
        // Set the size of the button to 60
        startButton.setMinSize(155, 57);
        startButton.setMaxSize(155, 57);

        // add the info button to the pane
        this.getChildren().add(infoButton);
        infoButton.setLayoutX(122);
        infoButton.setLayoutY(295);
        // set the font of the button to "Dialog", Font.BOLD, 12, remove the focus color
        infoButton.setStyle("-fx-font: 12 \"Dialog\"; -fx-font-weight: bold; -fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; -fx-background-radius: 2, 2, 2, 2;");
        // Set the size of the button to 60
        infoButton.setMinSize(155, 57);
        infoButton.setMaxSize(155, 57);


        // set the background to stopRightThereLogo.jpg, set the size of the background to a width of 500, 500 but start the background at -50, 0
        this.setStyle("-fx-background-image: url('stopRightThereLogo.jpg'); -fx-background-size: 500, 500; -fx-background-position: -50, 0;");

        // restrict the size of the pane to 500, 500
        this.setMinSize(500, 500);
        this.setMaxSize(500, 500);

        // make the start button create a new scene when it is clicked
        startButton.setOnAction(event -> {
            // Create a new instance of the Scene class and set it as the active scene
            hitsound();
            Scene levelSelectScene = new Scene(new LevelSelect(), 585, 360);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(levelSelectScene);
        });

        // make the start button make a sound when it is hovered over
        startButton.setOnMouseEntered(event -> {
            // create a new instance of the Sound class and play the sound
            startButton.setStyle("-fx-font: 18 \"Dialog\"; -fx-font-weight: bold; -fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; -fx-background-radius: 2, 2, 2, 2;");
            hitsound();
        });

        // make the start button change font when it is not hovered over
        startButton.setOnMouseExited(event -> {
            startButton.setStyle("-fx-font: 12 \"Dialog\"; -fx-font-weight: bold; -fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; -fx-background-radius: 2, 2, 2, 2;");
        });

        // make the info button create a new scene when it is clicked
        infoButton.setOnAction(event -> {
            // Create a new instance of the Scene class and set it as the active scene
            hitsound();
            Scene infoScene = new Scene(new InfoPage(), 640, 480);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(infoScene);
        });

        // make the info button make a sound when it is hovered over
        infoButton.setOnMouseEntered(event -> {
            // create a new instance of the Sound class and play the sound
            infoButton.setStyle("-fx-font: 18 \"Dialog\"; -fx-font-weight: bold; -fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; -fx-background-radius: 2, 2, 2, 2;");
            hitsound();
        });

        // make the info button change font when it is not hovered over
        infoButton.setOnMouseExited(event -> {
            infoButton.setStyle("-fx-font: 12 \"Dialog\"; -fx-font-weight: bold; -fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; -fx-background-radius: 2, 2, 2, 2;");
        });

    }

    public void hitsound() {
        String s = "src/main/resources/hitsound.wav";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }
}