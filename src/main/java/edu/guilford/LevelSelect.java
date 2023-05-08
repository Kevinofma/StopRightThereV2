package edu.guilford;

import java.nio.file.Paths;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import menuassets.MenuButton;
import menuassets.EasyLvlButton;
import menuassets.HardLvlButton;
import menuassets.MedLvlButton;

public class LevelSelect extends Pane {

    private EasyLvlButton easySong1;
    private EasyLvlButton easySong2;
    private MedLvlButton medSong1;
    private MedLvlButton medSong2;
    private HardLvlButton hardSong1;
    private HardLvlButton hardSong2;
    private Button hardcore;
    private Label hardcoreStatus;
    private Button chaos;
    private Label chaosStatus;
    private Button noFail;
    private Label noFailStatus;
    private Button returnToMenuButton;
    MediaPlayer hoverPlayer;
    MediaPlayer slidePlayer;

    static String songVideo = "MainMenuBackground.mp4";
    static String songFileName;
    static int songBPM;
    static int songDelay;
    static double beatsToDelayOffset = 0; // weird glitch where if if you return to menu, the SRT! button is not on beat

    private static boolean hardcoreDiff = false;
    private static boolean chaosDiff = false;
    private static boolean infiniteHealth = false;
    private static boolean songWithVideo = false;

    String song1EasyName = "Flower Dance";
    String song2EasyName = "The Stains of Time";
    String song1MedName = "Dynamite";
    String song2MedName = "Placeholder";
    String song1HardName = "Placeholder";
    String song2HardName = "Placeholder";

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
    String chaosStyleNormal = "-fx-font-size: 12px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: black; " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: white; " +
    "-fx-text-fill: white;";
    String chaosStyleBig = "-fx-font-size: 16px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: black; " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: white; " +
    "-fx-text-fill: white;";
    String noFailStyleNormal = "-fx-font-size: 12px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: black; " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: white; " +
    "-fx-text-fill: white;";
    String noFailStyleBig = "-fx-font-size: 16px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: black; " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: white; " +
    "-fx-text-fill: white;";       
    String menuButtonStyleNormal = "-fx-font-size: 12px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: black; " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: white; " +
    "-fx-text-fill: white;";
    String menuButtonStyleBig = "-fx-font-size: 16px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; " +
    "-fx-focus-color: transparent; -fx-border-color: black; -fx-border-width: 1px; " +
    "-fx-background-radius: 2 2 2 2; -fx-background-color: black; " +
    "-fx-border-style: solid; -fx-border-radius: 2 2 2 2; -fx-padding: 5px; " +
    "-fx-border-insets: 2px; -fx-border-width: 1px; -fx-border-color: white; " +
    "-fx-text-fill: white;";

    MediaPlayer mediaVideoPlayer;
    TranslateTransition easy1Transition;
    TranslateTransition easy2Transition;
    TranslateTransition med1Transition;
    TranslateTransition med2Transition;
    TranslateTransition hard1Transition;
    TranslateTransition hard2Transition;

    boolean easyDiff = true;
    boolean medDiff = false;
    boolean hardDiff = false;
    boolean transitionsRunning = false;

    MenuButton previousMenuButton;
    MenuButton nextMenuButton;
    
    //constructor
    public LevelSelect() {

        this.setStyle("-fx-background-image: url('MainMenuWallpaper.jpg'); -fx-background-size: 1250, 650; -fx-background-position: 0, 0;");
        levelSelectVideo();

        easySong1 = new EasyLvlButton(song1EasyName);
        easySong2 = new EasyLvlButton(song2EasyName);
        medSong1 = new  MedLvlButton(song1MedName);
        medSong2 = new MedLvlButton(song2MedName);
        hardSong1 = new HardLvlButton(song1HardName); 
        hardSong2 = new HardLvlButton(song2HardName);
        hardcore = new Button("Hardcore");
        hardcoreStatus = new Label("Hardcore: " + "OFF");
        chaos = new Button("Chaos");
        chaosStatus = new Label("Chaos: " + "OFF");
        noFail = new Button("No Fail");
        noFailStatus = new Label("No Fail: " + "OFF");
        returnToMenuButton = new Button("Back");
        previousMenuButton = new MenuButton();
        nextMenuButton = new MenuButton();

        easy1Transition = new TranslateTransition(Duration.millis(500));
        easy1Transition.setNode(easySong1);
        easy1Transition.setCycleCount(1);
        easy1Transition.setAutoReverse(false);
        
        easy2Transition = new TranslateTransition(Duration.millis(500));
        easy2Transition.setNode(easySong2);
        easy2Transition.setCycleCount(1);
        easy2Transition.setAutoReverse(false);

        med1Transition = new TranslateTransition(Duration.millis(500));
        med1Transition.setNode(medSong1);
        med1Transition.setCycleCount(1);
        med1Transition.setAutoReverse(false);

        med2Transition = new TranslateTransition(Duration.millis(500));
        med2Transition.setNode(medSong2);
        med2Transition.setCycleCount(1);
        med2Transition.setAutoReverse(false);

        hard1Transition = new TranslateTransition(Duration.millis(500));
        hard1Transition.setNode(hardSong1);
        hard1Transition.setCycleCount(1);
        hard1Transition.setAutoReverse(false);
        
        hard2Transition = new TranslateTransition(Duration.millis(500));
        hard2Transition.setNode(hardSong2);
        hard2Transition.setCycleCount(1);
        hard2Transition.setAutoReverse(false);

        previousMenuButton.setText("◀");
        previousMenuButton.setLayoutX(200);
        previousMenuButton.setLayoutY(400);
        this.getChildren().add(previousMenuButton);
        previousMenuButton.setOnMouseEntered(event -> {
            hoversound();
        });

        previousMenuButton.setOnMouseExited(event -> {
        });

        previousMenuButton.setOnAction(event -> {
            if (!easyDiff && medDiff && !areTransitionsRunning()) {
                med1Transition.setByX(-500);
                med1Transition.play();
                med2Transition.setByX(-500);
                med2Transition.play();
                easy1Transition.setByX(500);
                easy1Transition.play();
                easy2Transition.setByX(500);
                easy2Transition.play();
                medDiff = false;
                easyDiff = true;
                slidesound();
            } else if (!hardDiff && easyDiff && !areTransitionsRunning()) {
                easy1Transition.setByX(-500);
                easy1Transition.play();
                easy2Transition.setByX(-500);
                easy2Transition.play();
                hard1Transition.setByX(500);
                hard1Transition.play();
                hard2Transition.setByX(500);
                hard2Transition.play();
                easyDiff = false;
                hardDiff = true;
                slidesound();
            } else if (!medDiff && hardDiff && !areTransitionsRunning()) {
                hard1Transition.setByX(-500);
                hard1Transition.play();
                hard2Transition.setByX(-500);
                hard2Transition.play();
                med1Transition.setByX(500);
                med1Transition.play();
                med2Transition.setByX(500);
                med2Transition.play();
                hardDiff = false;
                medDiff = true;
                slidesound();
            }
        });

        nextMenuButton.setText("▶");
        nextMenuButton.setLayoutX(300);
        nextMenuButton.setLayoutY(400);
        this.getChildren().add(nextMenuButton);
        nextMenuButton.setOnMouseEntered(event -> {
            hoversound();
        });

        nextMenuButton.setOnMouseExited(event -> {
        });

        nextMenuButton.setOnAction(event -> {
            if (!medDiff && easyDiff && !areTransitionsRunning()) {
                easy1Transition.setByX(-500);
                easy1Transition.play();
                easy2Transition.setByX(-500);
                easy2Transition.play();
                med1Transition.setByX(500);
                med1Transition.play();
                med2Transition.setByX(500);
                med2Transition.play();
                medDiff = true;
                easyDiff = false;
                slidesound();
            } else if (!hardDiff && medDiff && !areTransitionsRunning()) {
                med1Transition.setByX(-500);
                med1Transition.play();
                med2Transition.setByX(-500);
                med2Transition.play();
                hard1Transition.setByX(500);
                hard1Transition.play();
                hard2Transition.setByX(500);
                hard2Transition.play();
                medDiff = false;
                hardDiff = true;
                slidesound();
            } else if (!easyDiff && hardDiff && !areTransitionsRunning()) {
                hard1Transition.setByX(-500);
                hard1Transition.play();
                hard2Transition.setByX(-500);
                hard2Transition.play();
                easy1Transition.setByX(500);
                easy1Transition.play();
                easy2Transition.setByX(500);
                easy2Transition.play();
                hardDiff = false;
                easyDiff = true;
                slidesound();
            }
        });

        easy1Transition.setByX(500); // automatically set easy buttons first
        easy2Transition.setByX(500);
        easy1Transition.play();
        easy2Transition.play();
        easyDiff = true;

        easySong1.setLayoutX(-550);
        easySong1.setLayoutY(200);
        easySong1.setMinSize(500, 80);
        this.getChildren().add(easySong1);
        easySong1.setOnMouseEntered(event -> {
            hoversound();
            songVideo = "FlowerDanceVideo.mp4";
            levelSelectVideo();
        });

        easySong1.setOnMouseExited(event -> {
        });

        easySong1.setOnAction(event -> {
            hoversound();
            StartMenuPane.stopThreads();
            songVideo = "FlowerDanceVideo.mp4";
            songFileName = "FlowerDance.wav";
            songBPM = 100;
            songDelay = 44;
            songWithVideo = false;
            Scene gameScene = new Scene(new GamePane(songVideo, songFileName), 1250, 650);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(gameScene);
        });

        
        this.getChildren().add(easySong2);
        easySong2.setLayoutX(-550);
        easySong2.setLayoutY(300);
        easySong2.setMinSize(500, 80);
        easySong2.setOnMouseEntered(event -> {
            hoversound();
            songVideo = "StainsOfTimeVideo.mp4";
            levelSelectVideo();
        });

        easySong2.setOnMouseExited(event -> {
        });

        easySong2.setOnAction(event -> {
            hoversound();
            StartMenuPane.stopThreads();
            songVideo = "StainsOfTimeVideo.mp4";
            songFileName = "The_Stains_of_Time.wav";
            songBPM = 100;
            songDelay = 5;
            songWithVideo = false;
            Scene gameScene = new Scene(new GamePane(songVideo, songFileName), 1250, 650);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(gameScene);
        });

        this.getChildren().add(medSong1);
        medSong1.setLayoutX(-550);
        medSong1.setLayoutY(200);
        medSong1.setMinSize(500, 80);
        medSong1.setOnMouseEntered(event -> {
            hoversound();
            songVideo = "DynamiteVideo.mp4";
            levelSelectVideo();
        });

        medSong1.setOnMouseExited(event -> {
        });

        medSong1.setOnAction(event -> {
            hoversound();
            StartMenuPane.stopThreads();
            songVideo = "DynamiteVideo.mp4";
            songFileName = "DynamiteVideo.mp4";
            songBPM = 120;
            songDelay = 14;
            songWithVideo = true;
            Scene gameScene = new Scene(new GamePane(songVideo, songFileName), 1250, 650);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(gameScene);
        });

        this.getChildren().add(medSong2);
        medSong2.setLayoutX(-550);
        medSong2.setLayoutY(300);
        medSong2.setMinSize(500, 80);
        medSong2.setOnMouseEntered(event -> {
            hoversound();
        });

        medSong2.setOnMouseExited(event -> {
        });

        this.getChildren().add(hardSong1);
        hardSong1.setLayoutX(-550);
        hardSong1.setLayoutY(200);
        hardSong1.setMinSize(500, 80);
        hardSong1.setOnMouseEntered(event -> {
            hoversound();
        });

        hardSong1.setOnMouseExited(event -> {
        });

        this.getChildren().add(hardSong2);
        hardSong2.setLayoutX(-550);
        hardSong2.setLayoutY(300);
        hardSong2.setMinSize(500, 80);
        hardSong2.setOnMouseEntered(event -> {
            hoversound();
        });

        hardSong2.setOnMouseExited(event -> {
        });

        this.getChildren().add(hardcore);
        hardcore.setLayoutX(475);
        hardcore.setLayoutY(310);
        hardcore.setMinSize(100, 40);
        hardcore.setMaxSize(100, 40);
        hardcore.setStyle(hardcoreStyleNormal);
        hardcore.setOnMouseEntered(event -> {
            hoversound();
            hardcore.setStyle(hardcoreStyleBig);
        });

        hardcore.setOnMouseExited(event -> {
            hardcore.setStyle(hardcoreStyleNormal);
        });
    
        hardcore.setOnAction(event -> {
            hoversound();
            if (hardcoreDiff == false) {
                hardcoreDiff = true;
                hardcoreStatus.setText("Hardcore: " + "ON");
            } else {
                hardcoreDiff = false;
                hardcoreStatus.setText("Hardcore: " + "OFF");
            }
        });

        this.getChildren().add(hardcoreStatus);
        hardcoreStatus.setLayoutX(485);
        hardcoreStatus.setLayoutY(290);
        hardcoreStatus.setStyle("-fx-font-size: 12px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; -fx-text-fill: white;");

        this.getChildren().add(chaos);
        chaos.setLayoutX(475);
        chaos.setLayoutY(350);
        chaos.setMinSize(100, 40);
        chaos.setMaxSize(100, 40);
        chaos.setStyle(chaosStyleNormal);
        chaos.setOnMouseEntered(event -> {
            hoversound();
            chaos.setStyle(chaosStyleBig);
        });

        chaos.setOnMouseExited(event -> {
            chaos.setStyle(chaosStyleNormal);
        });

        chaos.setOnAction(event -> {
            hoversound();
            if (chaosDiff == false) {
                chaosDiff = true;
                chaosStatus.setText("Chaos: " + "ON");
            } else {
                chaosDiff = false;
                chaosStatus.setText("Chaos: " + "OFF");
            }
        });

        this.getChildren().add(chaosStatus);
        chaosStatus.setLayoutX(485);
        chaosStatus.setLayoutY(330);
        chaosStatus.setStyle("-fx-font-size: 12px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; -fx-text-fill: white;");

        this.getChildren().add(noFail);
        noFail.setLayoutX(475);
        noFail.setLayoutY(390);
        noFail.setMinSize(100, 40);
        noFail.setMaxSize(100, 40);
        noFail.setStyle(noFailStyleNormal);
        noFail.setOnMouseEntered(event -> {
            hoversound();
            noFail.setStyle(noFailStyleBig);
        });

        noFail.setOnMouseExited(event -> {
            noFail.setStyle(noFailStyleNormal);
        });

        noFail.setOnAction(event -> {
            hoversound();
            if (infiniteHealth == false) {
                infiniteHealth = true;
                noFailStatus.setText("No Fail: " + "ON");
            } else {
                infiniteHealth = false;
                noFailStatus.setText("No Fail: " + "OFF");
            }
        });

        this.getChildren().add(noFailStatus);
        noFailStatus.setLayoutX(485);
        noFailStatus.setLayoutY(370);
        noFailStatus.setStyle("-fx-font-size: 12px; -fx-font-family: 'Dialog'; -fx-font-weight: bold; -fx-text-fill: white;");

        this.getChildren().add(returnToMenuButton);
        returnToMenuButton.setLayoutX(475);
        returnToMenuButton.setLayoutY(430);
        returnToMenuButton.setMinSize(100, 40);
        returnToMenuButton.setMaxSize(100, 40);
        returnToMenuButton.setStyle(menuButtonStyleNormal);
        returnToMenuButton.setOnMouseEntered(event -> {
            hoversound();
            returnToMenuButton.setStyle(menuButtonStyleBig);
        });

        returnToMenuButton.setOnMouseExited(event -> {
            returnToMenuButton.setStyle(menuButtonStyleNormal);
        });

        returnToMenuButton.setOnAction(event -> {
            hoversound();
            StartMenuPane.mainMenuPlayer.stop();
            beatsToDelayOffset = -1.5;
            Scene mainMenuScene = new Scene(new StartMenuPane(), 1250, 650);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(mainMenuScene);
        });
                  

    }

    //getter for hardcoreDiff
    public static boolean getHardcoreDiff() {
        return hardcoreDiff;
    }

    //getter for chaosDiff
    public static boolean getChaosDiff() {
        return chaosDiff;
    }

    //getter for infiniteHealth
    public static boolean getInfiniteHealth() {
        return infiniteHealth;
    }

    //getter for songVideo
    public static String getSongVideo() {
        return songVideo;
    }
    
    public void hoversound() {
        String s = "src/main/resources/2.wav";
        Media h = new Media(Paths.get(s).toUri().toString());
        hoverPlayer = new MediaPlayer(h);
        hoverPlayer.play();
    }

    public void slidesound() {
        String s = "src/main/resources/slide.wav";
        Media h = new Media(Paths.get(s).toUri().toString());
        slidePlayer = new MediaPlayer(h);
        slidePlayer.play();
    }

    public static int getBPM() {
        return songBPM;
    }

    public static int getSongDelay() {
        return songDelay;
    }

    public static String getSongFileName() {
        return songFileName;
    }

    public static boolean getSongWithVideo() {
        return songWithVideo;
    }

    public static double getBeatsToDelayOffset() {
        return beatsToDelayOffset;
    }

    public static void setBeatsToDelayOffset(double beatsToDelayOffset) {
        LevelSelect.beatsToDelayOffset = beatsToDelayOffset;
    }

    public void levelSelectVideo() {
        if (mediaVideoPlayer != null) {
            this.setStyle("-fx-background-color: black; -fx-background-size: 1250, 650; -fx-background-position: 0, 0;");
            mediaVideoPlayer.stop();
            mediaVideoPlayer.dispose();
        };
        Media mediaVideo = new Media(Paths.get("src/main/resources/" + songVideo).toUri().toString());
        mediaVideoPlayer = new MediaPlayer(mediaVideo);
        MediaView mediaVideoView = new MediaView(mediaVideoPlayer);
        mediaVideoView.setOpacity(0.8);
        mediaVideoPlayer.setAutoPlay(true);
        mediaVideoPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaVideoView.setFitWidth(1250);
        mediaVideoView.setFitHeight(750);
        mediaVideoPlayer.setOnEndOfMedia(() -> mediaVideoPlayer.seek(Duration.ZERO));
        mediaVideoPlayer.play();
        // add mediaVideoView to the back of the pane
        this.getChildren().add(0, mediaVideoView);
    }

    public boolean areTransitionsRunning() {
        boolean transitionsRunning = false;
        // THERE IS 100% A BETTER WAY TO DO THIS BUT I DON'T KNOW HOW
        if (easy1Transition.getStatus() == Animation.Status.RUNNING || easy2Transition.getStatus() == Animation.Status.RUNNING || med1Transition.getStatus() == Animation.Status.RUNNING || med2Transition.getStatus() == Animation.Status.RUNNING || hard1Transition.getStatus() == Animation.Status.RUNNING || hard2Transition.getStatus() == Animation.Status.RUNNING) {
            transitionsRunning = true;
        }
        return transitionsRunning;
    }
    
}
