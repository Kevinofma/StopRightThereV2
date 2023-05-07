package edu.guilford;

import java.nio.file.Paths;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class LevelSelect extends Pane {

    private Button song1;
    private Button song2;
    private Button song3;
    private Button song4;
    private Button song5;
    private Button song6;
    private Button hardcore;
    private Label hardcoreStatus;
    MediaPlayer mediaPlayer;

    private static boolean hardcoreDiff = false;

    String song1Name = "Flower Dance";
    String song2Name = "The Stains of Time";
    String song3Name = "Dynamite";
    String song4Name = "Placeholder";
    String song5Name = "Placeholder";
    String song6Name = "Placeholder";

    String easyStyleNormal = "-fx-font-size: 12px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: rgba(55, 235, 52, 1); " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: black; ";
    String easyStyleBig = "-fx-font-size: 16px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: rgba(55, 235, 52, 1); " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: black; ";
    String mediumStyleNormal = "-fx-font-size: 12px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: rgba(219, 116, 26, 1); " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: black; ";
    String mediumStyleBig = "-fx-font-size: 16px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: rgba(219, 116, 26, 1); " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: black; ";
    String hardStyleNormal = "-fx-font-size: 12px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: rgba(235, 52, 52, 1); " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: black; ";
    String hardStyleBig = "-fx-font-size: 16px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: rgba(235, 52, 52, 1); " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: black; ";
    String hardcoreStyleNormal = "-fx-font-size: 12px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: black; " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: white; " +
    "-fx-text-fill: white;";  
    String hardcoreStyleBig = "-fx-font-size: 16px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: black; " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: white; " +
    "-fx-text-fill: white;";
       

    
    //constructor
    public LevelSelect() {

        song1 = new Button(song1Name);
        song2 = new Button(song2Name);
        song3 = new Button(song3Name);
        song4 = new Button(song4Name);
        song5 = new Button(song5Name); 
        song6 = new Button(song6Name);
        hardcore = new Button("Hardcore");
        hardcoreStatus = new Label("Hardcore: " + "OFF");

        // place the song1 button at x = 20, y = 20 with a width of 300 and a height of 40
        this.getChildren().add(song1);
        song1.setLayoutX(20);
        song1.setLayoutY(20);
        song1.setMinSize(300, 40);
        song1.setMaxSize(300, 40);
        song1.setStyle(easyStyleNormal);
        // make the song1 button make a sound when it is hovered over and change font
        song1.setOnMouseEntered(event -> {
            hitsound();
            song1.setStyle(easyStyleBig);
        });

        // make the song1 button change back to normal when the mouse is no longer hovering over it
        song1.setOnMouseExited(event -> {
            song1.setStyle(easyStyleNormal);
        });

        // make the song1 button create a new scene when it is clicked
        song1.setOnAction(event -> {
            hitsound();
            Scene gameScene = new Scene(new Level1(), 1250, 650);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(gameScene);
        });

        // in the top left corner of the button, display the text "Easy"
        song1.setGraphic(new Label("Easy"));
        song1.getGraphic().setStyle(easyStyleNormal);

        // REPEAT THIS FOR ALL SONG BUTTONS
        
        // place the song2 button at x = 20, y = 70 with a width of 300 and a height of 40
        this.getChildren().add(song2);
        song2.setLayoutX(20);
        song2.setLayoutY(70);
        song2.setMinSize(300, 40);
        song2.setMaxSize(300, 40);
        song2.setStyle(easyStyleNormal);
        // make the song2 button make a sound when it is hovered over and change font
        song2.setOnMouseEntered(event -> {
            hitsound();
            song2.setStyle(easyStyleBig);
        });

        // make the song2 button change back to normal when the mouse is no longer hovering over it
        song2.setOnMouseExited(event -> {
            song2.setStyle(easyStyleNormal);
        });

        // in the top left corner of the button, display the text "Easy"
        song2.setGraphic(new Label("Easy"));
        song2.getGraphic().setStyle(easyStyleNormal);

        // place the song3 button at x = 20, y = 120 with a width of 300 and a height of 40
        this.getChildren().add(song3);
        song3.setLayoutX(20);
        song3.setLayoutY(120);
        song3.setMinSize(300, 40);
        song3.setMaxSize(300, 40);
        song3.setStyle(mediumStyleNormal);
        // make the song3 button make a sound when it is hovered over and change font
        song3.setOnMouseEntered(event -> {
            hitsound();
            song3.setStyle(mediumStyleBig);
        });

        // make the song3 button change back to normal when the mouse is no longer hovering over it
        song3.setOnMouseExited(event -> {
            song3.setStyle(mediumStyleNormal);
        });

        // in the top left corner of the button, display the text "Medium"
        song3.setGraphic(new Label("Medium"));
        song3.getGraphic().setStyle(mediumStyleNormal);        
        
        // place the song4 button at x = 20, y = 170 with a width of 300 and a height of 40
        this.getChildren().add(song4);
        song4.setLayoutX(20);
        song4.setLayoutY(170);
        song4.setMinSize(300, 40);
        song4.setMaxSize(300, 40);
        song4.setStyle(mediumStyleNormal);
        // make the song4 button make a sound when it is hovered over and change font
        song4.setOnMouseEntered(event -> {
            hitsound();
            song4.setStyle(mediumStyleBig);
        });

        // make the song4 button change back to normal when the mouse is no longer hovering over it
        song4.setOnMouseExited(event -> {
            song4.setStyle(mediumStyleNormal);
        });

        // in the top left corner of the button, display the text "Medium"
        song4.setGraphic(new Label("Medium"));
        song4.getGraphic().setStyle(mediumStyleNormal);

        // place the song5 button at x = 20, y = 220 with a width of 300 and a height of 40
        this.getChildren().add(song5);
        song5.setLayoutX(20);
        song5.setLayoutY(220);
        song5.setMinSize(300, 40);
        song5.setMaxSize(300, 40);
        song5.setStyle(hardStyleNormal);
        // make the song5 button make a sound when it is hovered over and change font
        song5.setOnMouseEntered(event -> {
            hitsound();
            song5.setStyle(hardStyleBig);
        });

        // make the song5 button change back to normal when the mouse is no longer hovering over it
        song5.setOnMouseExited(event -> {
            song5.setStyle(hardStyleNormal);
        });

        // in the top left corner of the button, display the text "Hard"
        song5.setGraphic(new Label("Hard"));
        song5.getGraphic().setStyle(hardStyleNormal);
        
        // place the song6 button at x = 20, y = 270 with a width of 300 and a height of 40
        this.getChildren().add(song6);
        song6.setLayoutX(20);
        song6.setLayoutY(270);
        song6.setMinSize(300, 40);
        song6.setMaxSize(300, 40);
        song6.setStyle(hardStyleNormal);
        // make the song6 button make a sound when it is hovered over and change font
        song6.setOnMouseEntered(event -> {
            hitsound();
            song6.setStyle(hardStyleBig);
        });

        // make the song6 button change back to normal when the mouse is no longer hovering over it
        song6.setOnMouseExited(event -> {
            song6.setStyle(hardStyleNormal);
        });

        // in the top left corner of the button, display the text "Hard"
        song6.setGraphic(new Label("Hard"));
        song6.getGraphic().setStyle(hardStyleNormal);

        // place the hardcore button in the bottom right of the screen with a width of 100 and a height of 40
        this.getChildren().add(hardcore);
        hardcore.setLayoutX(475);
        hardcore.setLayoutY(310);
        hardcore.setMinSize(100, 40);
        hardcore.setMaxSize(100, 40);
        hardcore.setStyle(hardcoreStyleNormal);
        // make the hardcore button make a sound when it is hovered over and change font
        hardcore.setOnMouseEntered(event -> {
            hitsound();
            hardcore.setStyle(hardcoreStyleBig);
        });

        // make the hardcore button change back to normal when the mouse is no longer hovering over it
        hardcore.setOnMouseExited(event -> {
            hardcore.setStyle(hardcoreStyleNormal);
        });
    
        // make the hardcore button change hardcoreDiff to true/false when it is clicked and change the text of hardcoreStatus to "Hardcore: ON/OFF"
        hardcore.setOnAction(event -> {
            hitsound();
            if (hardcoreDiff == false) {
                hardcoreDiff = true;
                hardcoreStatus.setText("Hardcore: " + "ON");
            } else {
                hardcoreDiff = false;
                hardcoreStatus.setText("Hardcore: " + "OFF");
            }
        });

        // put a label above the hardcore button that says "Hardcore"
        this.getChildren().add(hardcoreStatus);
        hardcoreStatus.setLayoutX(485);
        hardcoreStatus.setLayoutY(290);
        hardcoreStatus.setStyle("-fx-font-size: 12px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; -fx-text-fill: white;");
        
        
           
        //set the background to levelSelectBackground.jpg
        this.setStyle("-fx-background-image: url('levelSelectBackground.jpg'); -fx-background-size: 585, 360; -fx-background-position: 0, 0;");
       

    }

    //getter for hardcoreDiff
    public static boolean getHardcoreDiff() {
        return hardcoreDiff;
    }
    
    public void hitsound() {
        String s = "src/main/resources/hitsound.wav";
        //String s = "src/main/resources/100BPMMetronome.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }
    
}
