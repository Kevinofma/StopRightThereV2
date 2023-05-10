package edu.guilford;

import java.nio.file.Paths;

import TutorialSlideShow.Tutorial;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StartMenuPane extends Pane {

    private Button logoButton;
    private Button startButton;
    private Button tutorialButton;
    MediaPlayer mediaPlayer;
    public static MediaPlayer mainMenuPlayer;
    static MediaPlayer mediaVideoPlayer;
    boolean logoOut = false;
    TranslateTransition logoTransition;
    TranslateTransition startButtonTransition;
    ScaleTransition startButtonScaleTransition;
    ScaleTransition tutorialButtonScaleTransition;
    TranslateTransition startViewTransition;
    TranslateTransition tutorialViewTransition;

    // constructor
    public StartMenuPane() {

        this.setStyle("-fx-background-image: url('MainMenuWallpaper.jpg'); -fx-background-size: 1250, 650;");
        mainMenuMusic();
        mainMenuVideo();

        logoButton = new Button("SRT!");
        startButton = new Button();
        tutorialButton = new Button();

        // add the start button to the pane
        this.getChildren().add(startButton);
        startButton.setLayoutX(520);
        startButton.setLayoutY(130);
        startButton.setFont(Font.font("Impact", FontWeight.THIN, 50));
        startButton.setStyle("-fx-font-stretch: ultra-condensed;");
        startButton.setTextFill(Color.WHITE);
        BackgroundFill fill = new BackgroundFill(Color.rgb(128, 0, 128, 0.5), new CornerRadii(5), Insets.EMPTY);
        startButton.setBackground(new Background(fill));
        startButton.setOpacity(1);
        startButton.setMinSize(200, 150);

        // add the info button to the pane
        this.getChildren().add(tutorialButton);
        tutorialButton.setLayoutX(520);
        tutorialButton.setLayoutY(280);
        tutorialButton.setFont(Font.font("Impact", FontWeight.THIN, 50));
        tutorialButton.setStyle("-fx-font-stretch: ultra-condensed;");
        tutorialButton.setTextFill(Color.WHITE);
        tutorialButton.setBackground(new Background(fill));
        tutorialButton.setOpacity(1);
        tutorialButton.setMinSize(265, 150);

        ImageView startView = new ImageView(new Image("startLogo.png"));
        startView.setPreserveRatio(true);
        startView.setFitHeight(120);
        startView.setMouseTransparent(true);
        startView.setTranslateX(480);
        startView.setTranslateY(140);
        this.getChildren().add(startView);

        ImageView tutorialView = new ImageView(new Image("tutorialLogo.png"));
        tutorialView.setPreserveRatio(true);
        tutorialView.setFitHeight(120);
        tutorialView.setMouseTransparent(true);
        tutorialView.setTranslateX(480);
        tutorialView.setTranslateY(290);
        this.getChildren().add(tutorialView);

        logoButton.setStyle("-fx-font: 110 \"Impact\"; -fx-font-weight: bold;" +
                "-fx-background-color: pink;" +
                "-fx-background-radius: 240; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 15px; " +
                "-fx-border-radius: 240; " +
                "-fx-text-fill: white;");
        logoButton.setMinSize(480, 480);
        logoButton.setMaxSize(480, 480);
        logoButton.setLayoutX(400);
        logoButton.setLayoutY(100);
        getChildren().add(logoButton);

        double bpm = 140;
        double beatDuration = (60000 / bpm);
        //double beatsToDelay = 12 + LevelSelect.getBeatsToDelayOffset();
        double beatsToDelay = 11.5 + LevelSelect.getBeatsToDelayOffset();

        ScaleTransition pulseTransition = new ScaleTransition(Duration.millis(beatDuration), logoButton);
        pulseTransition.setFromX(1.2);
        pulseTransition.setFromY(1.2);
        pulseTransition.setToX(1);
        pulseTransition.setToY(1);
        pulseTransition.setCycleCount(Timeline.INDEFINITE);

        double delayDuration = (double) beatsToDelay * beatDuration;
        PauseTransition delay = new PauseTransition(Duration.millis(delayDuration));
        delay.setOnFinished(event -> {
            pulseTransition.play();
        });
        delay.play();
        System.out.println(delay.getStatus());

        logoTransition = new TranslateTransition(Duration.millis(250), logoButton);
        logoTransition.setCycleCount(1);

        startButtonTransition = new TranslateTransition(Duration.millis(250), startButton);
        startButtonTransition.setCycleCount(1);
        startButtonScaleTransition = new ScaleTransition(Duration.millis(250), startButton);
        startButtonScaleTransition.setCycleCount(1);

        startViewTransition = new TranslateTransition(Duration.millis(250), startView);
        startViewTransition.setCycleCount(1);

        tutorialButtonScaleTransition = new ScaleTransition(Duration.millis(250), tutorialButton);
        tutorialButtonScaleTransition.setCycleCount(1);

        tutorialViewTransition = new TranslateTransition(Duration.millis(250), tutorialView);
        tutorialViewTransition.setCycleCount(1);

        // restrict the size of the pane to 500, 500
        this.setMinSize(1250, 650);
        this.setMaxSize(1250, 500);

        // create a setOnAction event for the logoButton
        logoButton.setOnAction(event -> {
            if (logoOut && logoTransition.getStatus() != Timeline.Status.RUNNING) {
                logoInSound();
                logoTransition.setByX(250);
                logoTransition.play();
                startButtonTransition.setByX(-130);
                startButtonTransition.play();
                startButtonScaleTransition.setByX(-2);
                startButtonScaleTransition.play();
                startViewTransition.setByX(-200);
                startViewTransition.play();
                tutorialButtonScaleTransition.setByX(-2);
                tutorialButtonScaleTransition.play();
                tutorialViewTransition.setByX(-200);
                tutorialViewTransition.play();
                logoOut = false;
                System.out.println("LogoOut: " + logoOut);
            } else if (!logoOut && logoTransition.getStatus() != Timeline.Status.RUNNING) {
                logoOutSound();
                logoTransition.setByX(-250);
                logoTransition.play();
                startButtonTransition.setByX(130);
                startButtonTransition.setNode(startButton);
                startButtonTransition.play();
                startButtonScaleTransition.setByX(2);
                startButtonScaleTransition.play();
                startViewTransition.setByX(200);
                startViewTransition.play();
                tutorialButtonScaleTransition.setByX(2);
                tutorialButtonScaleTransition.play();
                tutorialViewTransition.setByX(200);
                tutorialViewTransition.play();
                logoOut = true;
                System.out.println("LogoOut: " + logoOut);
            }
        });

        // make the start button create a new scene when it is clicked
        startButton.setOnAction(event -> {
            // Create a new instance of the Scene class and set it as the active scene
            hoversound();
            Scene levelSelectScene = new Scene(new LevelSelect(), 1250, 650);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(levelSelectScene);
        });

        // make the start button make a sound when it is hovered over
        startButton.setOnMouseEntered(event -> {
            // create a new instance of the Sound class and play the sound
            hoversound();
            startButton.setText("                     "); // this is such a dumb way to do this
            startView.setFitHeight(startView.getFitHeight() + 10);
        });

        // make the start button change font when it is not hovered over
        startButton.setOnMouseExited(event -> {
            startButton.setText("");
            startView.setFitHeight(startView.getFitHeight() - 10);
        });

        // make the info button create a new scene when it is clicked
        tutorialButton.setOnAction(event -> {
            // Create a new instance of the Scene class and set it as the active scene
            hoversound();
            Scene infoScene = new Scene(new Tutorial(), 1250, 650);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(infoScene);
            // Scene ScoreCard = new Scene(new ScoreCard(), 1250, 650);
            // Stage Stage = (Stage) this.getScene().getWindow();
            // Stage.setScene(ScoreCard);
        });

        // make the info button make a sound when it is hovered over
        tutorialButton.setOnMouseEntered(event -> {
            tutorialButton.setText("                             ");
            tutorialView.setFitHeight(tutorialView.getFitHeight() + 10);
            hoversound();
        });

        // make the info button change font when it is not hovered over
        tutorialButton.setOnMouseExited(event -> {
            tutorialButton.setText("");
            tutorialView.setFitHeight(tutorialView.getFitHeight() - 10);
        });

    }

    public void hoversound() {
        String s = "src/main/resources/2.wav";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }

    public void mainMenuMusic() {
        String s = "src/main/resources/MeltyBloodMainMenuMusic.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mainMenuPlayer = new MediaPlayer(h);
        // set the volume to 50%
        mainMenuPlayer.setVolume(0.5 * GamePane.getVolumeLevel());
        mainMenuPlayer.play();
    }

    public static void stopMainMenuMusic() {
        if (mainMenuPlayer != null) {
        mainMenuPlayer.stop();
        }
    }

    public void mainMenuVideo() {
        Media mediaVideo = new Media(Paths.get("src/main/resources/MainMenuBackground.mp4").toUri().toString());
        mediaVideoPlayer = new MediaPlayer(mediaVideo);
        MediaView mediaVideoView = new MediaView(mediaVideoPlayer);
        mediaVideoView.setOpacity(0.4);
        mediaVideoPlayer.setAutoPlay(true);
        mediaVideoPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaVideoView.setFitWidth(1250);
        mediaVideoView.setFitHeight(750);
        mediaVideoPlayer.setOnEndOfMedia(() -> mediaVideoPlayer.seek(Duration.ZERO));
        mediaVideoPlayer.play();
        getChildren().add(mediaVideoView);
    }

    public void logoOutSound() {
        String s = "src/main/resources/home.wav";
        Media h = new Media(Paths.get(s).toUri().toString());
        MediaPlayer logoOutPlayer = new MediaPlayer(h);
        logoOutPlayer.play();
    }

    public void logoInSound() {
        String s = "src/main/resources/menuclick(4).wav";
        Media h = new Media(Paths.get(s).toUri().toString());
        MediaPlayer logoInPlayer = new MediaPlayer(h);
        logoInPlayer.play();
    }

    public static void stopThreads() {
        mainMenuPlayer.stop();
        mediaVideoPlayer.stop();
    }
}