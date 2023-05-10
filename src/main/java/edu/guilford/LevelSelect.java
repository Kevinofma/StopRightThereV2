package edu.guilford;

import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import menuassets.MenuButton;
import menuassets.InfoText;
import menuassets.ArrowButton;
import menuassets.DifficultyText;
import menuassets.EasyLvlButton;
import menuassets.HardLvlButton;
import menuassets.InfoRectangle;
import menuassets.MedLvlButton;

public class LevelSelect extends Pane {

    private EasyLvlButton easySong1;
    private EasyLvlButton easySong2;
    private MedLvlButton medSong1;
    private MedLvlButton medSong2;
    private HardLvlButton hardSong1;
    private HardLvlButton hardSong2;
    private MenuButton hardcore;
    private MenuButton chaos;
    private MenuButton noFail;
    private MenuButton returnToMenuButton;
    MediaPlayer hoverPlayer;
    MediaPlayer slidePlayer;

    static String songVideo = "MainMenuBackground.mp4";
    static String songFileName;
    static int songBPM;
    static String songDuration;
    static int difficultyStars;
    static int songDelay;
    static double beatsToDelayOffset = 0; // weird glitch where if if you return to menu, the SRT! button is not on beat

    static String mods;
    private static boolean hardcoreDiff = false;
    private static boolean chaosDiff = false;
    private static boolean infiniteHealth = false;
    private static boolean songWithVideo = false;

    static String songName;
    String song1EasyName = "Life is a Highway";
    String song2EasyName = "The Stains of Time";
    String song1MedName = "Dynamite";
    String song2MedName = "Placeholder";
    String song1HardName = "Placeholder";
    String song2HardName = "Placeholder";

    MediaPlayer mediaVideoPlayer;
    TranslateTransition easy1Transition;
    TranslateTransition easy2Transition;
    TranslateTransition med1Transition;
    TranslateTransition med2Transition;
    TranslateTransition hard1Transition;
    TranslateTransition hard2Transition;
    TranslateTransition easyTextTransition;
    TranslateTransition medTextTransition;
    TranslateTransition hardTextTransition;

    static String difficulty;
    static boolean easyDiff = true;
    static boolean medDiff = false;
    static boolean hardDiff = false;
    boolean transitionsRunning = false;

    ArrowButton previousMenuButton;
    ArrowButton nextMenuButton;

    InfoRectangle infoRect;
    Rectangle topBorderRect;
    Rectangle bottomBorderRect;

    DifficultyText easyText;
    DifficultyText medText;
    DifficultyText hardText;
    InfoText bpmText;
    InfoText durationText;
    InfoText difficultyInfoText;

    ImageView diffStar1;
    ImageView diffStar2;
    ImageView diffStar3;
    ImageView diffStar4;
    ImageView diffStar5;
    ArrayList<ImageView> starsList = new ArrayList<ImageView>(); // adapted from lines arrayList in GamePane.java

    // constructor
    public LevelSelect() {

        this.setStyle("-fx-background-image: url('MainMenuWallpaper.jpg'); -fx-background-size: 1250, 650; -fx-background-position: 0, 0;");
        // this.setStyle("-fx-background-image: url('LevelSelectBlueprint.PNG'); -fx-background-size: 1250, 650; -fx-background-position: 0, 0;");
        levelSelectVideo();

        hardcoreDiff = false;
        chaosDiff = false;
        infiniteHealth = false;

        easySong1 = new EasyLvlButton(song1EasyName);
        easySong2 = new EasyLvlButton(song2EasyName);
        medSong1 = new MedLvlButton(song1MedName);
        medSong2 = new MedLvlButton(song2MedName);
        hardSong1 = new HardLvlButton(song1HardName);
        hardSong2 = new HardLvlButton(song2HardName);
        easyText = new DifficultyText("Easy");
        medText = new DifficultyText("Medium");
        hardText = new DifficultyText("Hard");
        infoRect = new InfoRectangle();
        topBorderRect = new Rectangle();
        bottomBorderRect = new Rectangle();
        bpmText = new InfoText("BPM: ");
        durationText = new InfoText("Duration: ");
        difficultyInfoText = new InfoText("Difficulty: ");
        hardcore = new MenuButton();
        chaos = new MenuButton();
        noFail = new MenuButton();
        returnToMenuButton = new MenuButton();
        previousMenuButton = new ArrowButton("《");
        nextMenuButton = new ArrowButton("》");

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

        easyTextTransition = new TranslateTransition(Duration.millis(500));
        easyTextTransition.setNode(easyText);
        easyTextTransition.setCycleCount(1);
        easyTextTransition.setAutoReverse(false);

        medTextTransition = new TranslateTransition(Duration.millis(500));
        medTextTransition.setNode(medText);
        medTextTransition.setCycleCount(1);
        medTextTransition.setAutoReverse(false);

        hardTextTransition = new TranslateTransition(Duration.millis(500));
        hardTextTransition.setNode(hardText);
        hardTextTransition.setCycleCount(1);
        hardTextTransition.setAutoReverse(false);

        previousMenuButton.setLayoutX(55);
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
                medTextTransition.setByX(-500);
                medTextTransition.play();
                easyTextTransition.setByX(500);
                easyTextTransition.play();
                infoRect.setGradColor(Color.GREEN);
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
                easyTextTransition.setByX(-500);
                easyTextTransition.play();
                hardTextTransition.setByX(500);
                hardTextTransition.play();
                infoRect.setGradColor(Color.RED);
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
                hardTextTransition.setByX(-500);
                hardTextTransition.play();
                medTextTransition.setByX(500);
                medTextTransition.play();
                infoRect.setGradColor(Color.ORANGE);
                hardDiff = false;
                medDiff = true;
                slidesound();
            }
        });

        nextMenuButton.setLayoutX(242);
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
                easyTextTransition.setByX(-500);
                easyTextTransition.play();
                medTextTransition.setByX(500);
                medTextTransition.play();
                infoRect.setGradColor(Color.ORANGE);
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
                medTextTransition.setByX(-500);
                medTextTransition.play();
                hardTextTransition.setByX(500);
                hardTextTransition.play();
                infoRect.setGradColor(Color.RED);
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
                hardTextTransition.setByX(-500);
                hardTextTransition.play();
                easyTextTransition.setByX(500);
                easyTextTransition.play();
                infoRect.setGradColor(Color.GREEN);
                hardDiff = false;
                easyDiff = true;
                slidesound();
            }
        });

        easy1Transition.setByX(500); // automatically set easy buttons first
        easy2Transition.setByX(500);
        easy1Transition.play();
        easy2Transition.play();
        easyTextTransition.setByX(500);
        easyTextTransition.play();
        easyDiff = true;

        easyText.setLayoutX(-380);
        easyText.setLayoutY(150);
        easyText.setFill(Color.GREEN);
        getChildren().add(easyText);

        medText.setLayoutX(-440);
        medText.setLayoutY(150);
        medText.setFill(Color.ORANGE);
        getChildren().add(medText);

        hardText.setLayoutX(-380);
        hardText.setLayoutY(150);
        hardText.setFill(Color.RED);
        getChildren().add(hardText);

        easySong1.setLayoutX(-550);
        easySong1.setLayoutY(200);
        easySong1.setMinSize(500, 80);
        this.getChildren().add(easySong1);
        easySong1.setOnMouseEntered(event -> {
            hoversound();
            songVideo = "HighwaySong.mp4";
            levelSelectVideo();
            easySong1.setMinSize(600, 80);
            songBPM = 103;
            songDuration = "4:30";
            difficultyStars = 1;
            updateInfo();
        });

        easySong1.setOnMouseExited(event -> {
            easySong1.setMinSize(500, 80);
        });

        easySong1.setOnAction(event -> {
            hoversound();
            StartMenuPane.stopThreads();
            songVideo = "HighwaySong.mp4";
            songFileName = "HighwaySong.mp4";
            songBPM = 103;
            songDelay = 48;
            songWithVideo = true;
            songName = song1EasyName;
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
            easySong2.setMinSize(600, 80);
            songBPM = 100;
            songDuration = "2:10";
            difficultyStars = 2;
            updateInfo();
        });

        easySong2.setOnMouseExited(event -> {
            easySong2.setMinSize(500, 80);
        });

        easySong2.setOnAction(event -> {
            hoversound();
            StartMenuPane.stopThreads();
            songVideo = "StainsOfTimeVideo.mp4";
            songFileName = "The_Stains_of_Time.wav";
            songBPM = 100;
            songDelay = 5;
            songWithVideo = false;
            songName = song2EasyName;
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
            medSong1.setMinSize(600, 80);
            songBPM = 120;
            songDuration = "3:21";
            difficultyStars = 3;
            updateInfo();
        });

        medSong1.setOnMouseExited(event -> {
            medSong1.setMinSize(500, 80);
        });

        medSong1.setOnAction(event -> {
            hoversound();
            StartMenuPane.stopThreads();
            songVideo = "DynamiteVideo.mp4";
            songFileName = "DynamiteVideo.mp4";
            songBPM = 120;
            songDelay = 14;
            songWithVideo = true;
            songName = song1MedName;
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
            medSong2.setMinSize(600, 80);
        });

        medSong2.setOnMouseExited(event -> {
            medSong2.setMinSize(500, 80);
        });

        this.getChildren().add(hardSong1);
        hardSong1.setLayoutX(-550);
        hardSong1.setLayoutY(200);
        hardSong1.setMinSize(500, 80);
        hardSong1.setOnMouseEntered(event -> {
            hoversound();
            hardSong1.setMinSize(600, 80);
        });

        hardSong1.setOnMouseExited(event -> {
            hardSong1.setMinSize(500, 80);
        });

        this.getChildren().add(hardSong2);
        hardSong2.setLayoutX(-550);
        hardSong2.setLayoutY(300);
        hardSong2.setMinSize(500, 80);
        hardSong2.setOnMouseEntered(event -> {
            hoversound();
            hardSong2.setMinSize(600, 80);
        });

        hardSong2.setOnMouseExited(event -> {
            hardSong2.setMinSize(500, 80);
        });

        topBorderRect.setWidth(1250);
        topBorderRect.setHeight(35);
        topBorderRect.setLayoutX(0);
        topBorderRect.setLayoutY(0);
        topBorderRect.setFill(Color.rgb(10, 10, 10));
        topBorderRect.setStroke(Color.BLACK);
        topBorderRect.setStrokeWidth(10);
        topBorderRect.setOpacity(1);
        this.getChildren().add(topBorderRect);

        bottomBorderRect.setWidth(1250);
        bottomBorderRect.setHeight(80);
        bottomBorderRect.setLayoutX(0);
        bottomBorderRect.setLayoutY(570);
        bottomBorderRect.setFill(Color.rgb(10, 10, 10));
        bottomBorderRect.setStroke(Color.BLACK);
        bottomBorderRect.setStrokeWidth(10);
        bottomBorderRect.setOpacity(1);
        this.getChildren().add(bottomBorderRect);

        infoRect.setWidth(600);
        infoRect.setHeight(630);
        infoRect.setOpacity(0.75);
        infoRect.setLayoutX(850);
        infoRect.setLayoutY(10);
        infoRect.setGradColor(Color.GREEN);
        infoRect.setStroke(Color.BLACK);
        infoRect.setStrokeWidth(10);
        infoRect.setArcWidth(50);
        infoRect.setArcHeight(50);
        this.getChildren().add(infoRect);

        ImageView bpmIcon = new ImageView(new Image("music-note.png"));
        bpmIcon.setFitWidth(80);
        bpmIcon.setPreserveRatio(true);
        bpmIcon.setLayoutX(880);
        bpmIcon.setLayoutY(110);
        this.getChildren().add(bpmIcon);

        bpmText.setText("BPM: " + "???");
        bpmText.setLayoutX(980);
        bpmText.setLayoutY(170);
        getChildren().add(bpmText);

        ImageView durationIcon = new ImageView(new Image("clock.png"));
        durationIcon.setFitWidth(80);
        durationIcon.setPreserveRatio(true);
        durationIcon.setLayoutX(880);
        durationIcon.setLayoutY(240);
        this.getChildren().add(durationIcon);

        durationText.setText("Duration: \n" + "?:??");
        durationText.setLayoutX(980);
        durationText.setLayoutY(270);
        getChildren().add(durationText);

        ImageView difficultyIcon = new ImageView(new Image("star.png"));
        difficultyIcon.setFitWidth(80);
        difficultyIcon.setPreserveRatio(true);
        difficultyIcon.setLayoutX(880);
        difficultyIcon.setLayoutY(365);
        this.getChildren().add(difficultyIcon);

        difficultyInfoText.setText("Difficulty: " + "?");
        difficultyInfoText.setLayoutX(980);
        difficultyInfoText.setLayoutY(430);
        getChildren().add(difficultyInfoText);

        diffStar1 = new ImageView(new Image("starHollow.png"));
        diffStar1.setFitWidth(60);
        diffStar1.setPreserveRatio(true);
        diffStar1.setLayoutX(880);
        diffStar1.setLayoutY(500);
        this.getChildren().add(diffStar1);

        diffStar2 = new ImageView(new Image("starHollow.png"));
        diffStar2.setFitWidth(60);
        diffStar2.setPreserveRatio(true);
        diffStar2.setLayoutX(950);
        diffStar2.setLayoutY(500);
        this.getChildren().add(diffStar2);

        diffStar3 = new ImageView(new Image("starHollow.png"));
        diffStar3.setFitWidth(60);
        diffStar3.setPreserveRatio(true);
        diffStar3.setLayoutX(1020);
        diffStar3.setLayoutY(500);
        this.getChildren().add(diffStar3);

        diffStar4 = new ImageView(new Image("starHollow.png"));
        diffStar4.setFitWidth(60);
        diffStar4.setPreserveRatio(true);
        diffStar4.setLayoutX(1090);
        diffStar4.setLayoutY(500);
        this.getChildren().add(diffStar4);

        diffStar5 = new ImageView(new Image("starHollow.png"));
        diffStar5.setFitWidth(60);
        diffStar5.setPreserveRatio(true);
        diffStar5.setLayoutX(1160);
        diffStar5.setLayoutY(500);
        this.getChildren().add(diffStar5);

        this.getChildren().add(hardcore);
        hardcore.setText("Hardcore: " + "OFF");
        hardcore.setLayoutX(260);
        hardcore.setLayoutY(590);
        hardcore.setMinSize(120, 40);
        hardcore.setOnMouseEntered(event -> {
            hoversound();
            hardcore.setLayoutX(255);
            hardcore.setLayoutY(585);
            hardcore.setMinSize(130, 50);
        });

        hardcore.setOnMouseExited(event -> {
            hardcore.setLayoutX(260);
            hardcore.setLayoutY(590);
            hardcore.setMinSize(120, 40);
        });

        hardcore.setOnAction(event -> {
            hoversound();
            if (hardcoreDiff == false) {
                hardcoreDiff = true;
                hardcore.setText("Hardcore: " + "ON");
            } else if (hardcoreDiff == true) {
                hardcoreDiff = false;
                hardcore.setText("Hardcore: " + "OFF");
            }
        });

        this.getChildren().add(chaos);
        chaos.setText("Chaos: " + "OFF");
        chaos.setLayoutX(450);
        chaos.setLayoutY(590);
        chaos.setMinSize(120, 40);
        chaos.setOnMouseEntered(event -> {
            hoversound();
            chaos.setLayoutX(445);
            chaos.setLayoutY(585);
            chaos.setMinSize(130, 50);
        });

        chaos.setOnMouseExited(event -> {
            chaos.setLayoutX(450);
            chaos.setLayoutY(590);
            chaos.setMinSize(120, 40);
        });

        chaos.setOnAction(event -> {
            hoversound();
            if (chaosDiff == false) {
                chaosDiff = true;
                chaos.setText("Chaos: " + "ON");
            } else if (chaosDiff == true) {
                chaosDiff = false;
                chaos.setText("Chaos: " + "OFF");
            }
        });

        this.getChildren().add(noFail);
        noFail.setText("No Fail: " + "OFF");
        noFail.setLayoutX(640);
        noFail.setLayoutY(590);
        noFail.setMinSize(120, 40);
        noFail.setOnMouseEntered(event -> {
            hoversound();
            noFail.setLayoutX(635);
            noFail.setLayoutY(585);
            noFail.setMinSize(130, 50);
        });

        noFail.setOnMouseExited(event -> {
            noFail.setLayoutX(640);
            noFail.setLayoutY(590);
            noFail.setMinSize(120, 40);
        });

        noFail.setOnAction(event -> {
            hoversound();
            if (infiniteHealth == false) {
                infiniteHealth = true;
                noFail.setText("No Fail: " + "ON");
            } else if (infiniteHealth == true) {
                infiniteHealth = false;
                noFail.setText("No Fail: " + "OFF");
            }
        });

        this.getChildren().add(returnToMenuButton);
        returnToMenuButton.setText("Return");
        returnToMenuButton.setLayoutX(75);
        returnToMenuButton.setLayoutY(590);
        returnToMenuButton.setMinSize(120, 40);
        returnToMenuButton.setOnMouseEntered(event -> {
            hoversound();
            returnToMenuButton.setLayoutX(70);
            returnToMenuButton.setLayoutY(585);
            returnToMenuButton.setMinSize(130, 50);
        });

        returnToMenuButton.setOnMouseExited(event -> {
            returnToMenuButton.setLayoutX(75);
            returnToMenuButton.setLayoutY(590);
            returnToMenuButton.setMinSize(120, 40);
        });

        returnToMenuButton.setOnAction(event -> {
            hoversound();
            StartMenuPane.mainMenuPlayer.stop();
            beatsToDelayOffset = -2;
            Scene mainMenuScene = new Scene(new StartMenuPane(), 1250, 650);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(mainMenuScene);
        });

    }

    // getter for hardcoreDiff
    public static boolean getHardcoreDiff() {
        return hardcoreDiff;
    }

    // getter for chaosDiff
    public static boolean getChaosDiff() {
        return chaosDiff;
    }

    // getter for infiniteHealth
    public static boolean getInfiniteHealth() {
        return infiniteHealth;
    }

    // getter for songVideo
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
            this.setStyle("-fx-background-color: gray; -fx-background-size: 1250, 650; -fx-background-position: 0, 0;");
            mediaVideoPlayer.stop();
            mediaVideoPlayer.dispose();
        }
        ;
        Media mediaVideo = new Media(Paths.get("src/main/resources/" + songVideo).toUri().toString());
        mediaVideoPlayer = new MediaPlayer(mediaVideo);
        MediaView mediaVideoView = new MediaView(mediaVideoPlayer);
        mediaVideoView.setOpacity(0.4);
        mediaVideoPlayer.setAutoPlay(true);
        mediaVideoPlayer.setVolume(0);
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
        if (easy1Transition.getStatus() == Animation.Status.RUNNING
                || easy2Transition.getStatus() == Animation.Status.RUNNING
                || med1Transition.getStatus() == Animation.Status.RUNNING
                || med2Transition.getStatus() == Animation.Status.RUNNING
                || hard1Transition.getStatus() == Animation.Status.RUNNING
                || hard2Transition.getStatus() == Animation.Status.RUNNING) {
            transitionsRunning = true;
        }
        return transitionsRunning;
    }

    public void updateInfo() {
        bpmText.setText("BPM: " + songBPM);
        durationText.setText("Duration: \n" + songDuration);
        difficultyInfoText.setText("Difficulty: " + difficultyStars);

        getChildren().removeAll(starsList);
        starsList.clear();
        for (int i = 0; i < difficultyStars; i++) {
            ImageView star = new ImageView(new Image("star.png"));
            star.setFitWidth(60);
            star.setPreserveRatio(true);
            star.setLayoutX(880 + (70 * i));
            star.setLayoutY(500);
            starsList.add(star);
            this.getChildren().add(star);
        }
    }

    public static String getSongName() {
        return songName;
    }

    public static String getDifficulty() {
        if (easyDiff && !medDiff && !hardDiff) {
            difficulty = "Easy";
        } else if (!easyDiff && medDiff && !hardDiff) {
            difficulty = "Medium";
        } else if (!easyDiff && !medDiff && hardDiff) {
            difficulty = "Hard";
        } else {
            difficulty = "Error calculating difficulty";
        }
        return difficulty;
    }

    public static String getMods() {
        if (hardcoreDiff && !chaosDiff && !infiniteHealth) {
            mods = "Hardcore";
        } else if (!hardcoreDiff && chaosDiff && !infiniteHealth) {
            mods = "Chaos";
        } else if (!hardcoreDiff && !chaosDiff && infiniteHealth) {
            mods = "No Fail";
        } else if (hardcoreDiff && chaosDiff && !infiniteHealth) {
            mods = "Hardcore, Chaos";
        } else if (hardcoreDiff && !chaosDiff && infiniteHealth) {
            mods = "Hardcore, No Fail";
        } else if (!hardcoreDiff && chaosDiff && infiniteHealth) {
            mods = "Chaos, No Fail";
        } else if (hardcoreDiff && chaosDiff && infiniteHealth) {
            mods = "Hardcore, Chaos, No Fail";
        } else {
            mods = "None";
        }
        return mods;
    }

    public static int getDifficultyStars() {
        return difficultyStars;
    }

}
