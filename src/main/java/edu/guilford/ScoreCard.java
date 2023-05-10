package edu.guilford;

import java.nio.file.Paths;

import TutorialSlideShow.Tutorial;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import menuassets.MenuButton;

public class ScoreCard extends Pane {
    // attributes
    private int hits;
    private int misses;
    private double accuracy;
    private int maxCombo;
    private int maxClauseIndex;
    private String maxClause;
    private int score;
    private String grade;
    private String songName;
    private String difficulty;
    private String mods;

    private MediaPlayer mediaVideoPlayer;

    private Rectangle dimRectangle;
    private Rectangle topBorderRectangleLeft;
    private Rectangle topBorderRectangleRight;
    private Rectangle scoreRectangle;
    private Text scoreText;
    private Text scoreNumText;
    private Text songNameText;
    private Text difficultyText;
    private Text accuracyText;
    private Text accuracyNumText;
    private Text hitText;
    private Text hitNumText;
    private Text missText;
    private Text missNumText;
    private Text modsTitleText;
    private Text modsText;
    private Text comboText;
    private Text comboNumText;
    private Text clauseText;
    private Text rankingText;
    private Text gradeText;

    // constructor
    public ScoreCard() {

        hits = GamePane.getNumHits();
        misses = GamePane.getNumMisses();
        accuracy = GamePane.getAccuracy();
        maxCombo = GamePane.getMaxCombo();
        maxClauseIndex = GamePane.getMaxClause();
        maxClause = this.calculateClause(maxClauseIndex);
        score = GamePane.getScore();
        grade = calculateGrade();
        songName = LevelSelect.getSongName();
        difficulty = LevelSelect.getDifficulty();
        mods = LevelSelect.getMods();

        System.out.println("hits: " + hits);
        System.out.println("misses: " + misses);
        System.out.println("accuracy: " + accuracy);
        System.out.println("maxCombo: " + maxCombo);
        System.out.println("maxClause: " + maxClauseIndex);
        System.out.println("score: " + score);
        System.out.println("grade: " + grade);
        System.out.println("songName: " + songName);
        System.out.println("difficulty: " + difficulty);
        System.out.println("mods: " + mods);

        boolean exampleScore = Tutorial.getExampleScore();
        if (exampleScore) {
        score = 500000;
        songName = "Song Name";
        difficulty = "Difficulty";
        accuracy = 83.4;
        hits = 1000;
        misses = 58;
        mods = "Hardcore Chaos";
        maxCombo = 50;
        maxClauseIndex = 3;
        maxClause = calculateClause(maxClauseIndex);
        grade = calculateGrade();
        exampleScore = false;
        Tutorial.setExampleScore(exampleScore);
        }

        mainMenuVideo();
        // this.setStyle("-fx-background-image: url('ScoreCardBlueprint.PNG');
        // -fx-background-size: 1250, 650; -fx-background-position: 0, 0;");
        this.setStyle(
                "-fx-background-image: url('MainMenuWallpaper.jpg'); -fx-background-size: 1250, 650; -fx-background-position: 0, 0;");

        dimRectangle = new Rectangle(360, 0, 500, 650);
        dimRectangle.setFill(Color.rgb(0, 0, 0, 0.9));
        getChildren().add(dimRectangle);

        topBorderRectangleLeft = new Rectangle(0, 0, 360, 110);
        topBorderRectangleLeft.setFill(Color.rgb(0, 0, 0, 0.9));
        getChildren().add(topBorderRectangleLeft);

        topBorderRectangleRight = new Rectangle(860, 0, 500, 110);
        topBorderRectangleRight.setFill(Color.rgb(0, 0, 0, 0.9));
        getChildren().add(topBorderRectangleRight);

        scoreRectangle = new Rectangle(365, 5, 490, 100);
        // round the edges
        scoreRectangle.setArcWidth(20);
        scoreRectangle.setArcHeight(20);
        scoreRectangle.setOpacity(1);
        // Add a gradient to the rectangle
        LinearGradient scoreGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.REFLECT,
                new Stop(0.2, Color.WHEAT), new Stop(1, Color.BEIGE));
        scoreRectangle.setFill(scoreGradient);
        getChildren().add(scoreRectangle);

        scoreText = new Text(375, 75, "Score: ");
        scoreText.setFill(Color.TAN);
        scoreText.setStyle("-fx-font: 60 arial;");
        getChildren().add(scoreText);

        scoreNumText = new Text(575, 78, Integer.toString(score));
        scoreNumText.setFill(Color.WHITE);
        scoreNumText.setStyle("-fx-font: 70 arial;");
        scoreNumText.setStroke(Color.DARKGRAY);
        scoreNumText.setStrokeWidth(2);
        getChildren().add(scoreNumText);

        songNameText = new Text(20, 40, songName);
        songNameText.setFill(Color.SILVER);
        songNameText.setStyle("-fx-font: 30 verdana;");
        getChildren().add(songNameText);

        difficultyText = new Text(20, 85, difficulty);
        difficultyText.setFill(Color.WHITE);
        difficultyText.setStyle("-fx-font: 40 verdana;");
        getChildren().add(difficultyText);

        accuracyText = new Text(380, 165, "Accuracy: ");
        accuracyText.setFill(Color.WHITE);
        accuracyText.setStyle("-fx-font: 30 verdana;");
        accuracyText.setEffect(new DropShadow(10, 0, 0, Color.SKYBLUE));
        accuracyText.setStroke(Color.SKYBLUE);
        accuracyText.setStrokeWidth(1);
        getChildren().add(accuracyText);

        accuracyNumText = new Text(580, 168, String.format("%.2f%%", accuracy));
        accuracyNumText.setFill(Color.WHITE);
        accuracyNumText.setStyle("-fx-font: 40 verdana;");
        accuracyNumText.setStroke(Color.BLACK);
        accuracyNumText.setStrokeWidth(0.5);
        // accuracyNumText.setScaleX(10);
        // accuracyNumText.setScaleY(10);
        accuracyNumText.setOpacity(0);
        getChildren().add(accuracyNumText);

        hitText = new Text(380, 225, "Notes Hit: ");
        hitText.setFill(Color.WHITE);
        hitText.setStyle("-fx-font: 30 verdana;");
        hitText.setEffect(new DropShadow(10, 0, 0, Color.LIGHTGREEN));
        hitText.setStroke(Color.LIGHTGREEN);
        hitText.setStrokeWidth(1);
        getChildren().add(hitText);

        hitNumText = new Text(580, 228, Integer.toString(hits));
        hitNumText.setFill(Color.WHITE);
        hitNumText.setStyle("-fx-font: 40 verdana;");
        hitNumText.setStroke(Color.BLACK);
        hitNumText.setStrokeWidth(0.5);
        hitNumText.setOpacity(0);
        getChildren().add(hitNumText);

        missText = new Text(380, 285, "Notes Missed: ");
        missText.setFill(Color.WHITE);
        missText.setStyle("-fx-font: 30 verdana;");
        missText.setEffect(new DropShadow(10, 0, 0, Color.RED));
        missText.setStroke(Color.RED);
        missText.setStrokeWidth(1);
        getChildren().add(missText);

        missNumText = new Text(630, 288, Integer.toString(misses));
        missNumText.setFill(Color.WHITE);
        missNumText.setStyle("-fx-font: 40 verdana;");
        missNumText.setStroke(Color.BLACK);
        missNumText.setStrokeWidth(0.5);
        missNumText.setOpacity(0);
        getChildren().add(missNumText);

        modsTitleText = new Text(560, 355, "Mods: ");
        modsTitleText.setFill(Color.WHITE);
        modsTitleText.setStyle("-fx-font: 30 verdana;");
        modsTitleText.setEffect(new DropShadow(10, 0, 0, Color.WHITE));
        modsTitleText.setStroke(Color.WHITE);
        modsTitleText.setStrokeWidth(1);
        getChildren().add(modsTitleText);

        modsText = new Text(380, 415, mods);
        modsText.setFill(Color.SILVER);
        modsText.setStyle("-fx-font: 35 verdana;");
        modsText.setEffect(new DropShadow(10, 0, 0, Color.DEEPPINK));
        modsText.setStroke(Color.DEEPPINK);
        modsText.setStrokeWidth(1);
        getChildren().add(modsText);

        comboText = new Text(380, 485, "Max Combo: ");
        comboText.setFill(Color.WHITE);
        comboText.setStyle("-fx-font: 30 verdana;");
        comboText.setEffect(new DropShadow(10, 0, 0, Color.GOLD));
        comboText.setStroke(Color.GOLD);
        comboText.setStrokeWidth(1);
        getChildren().add(comboText);

        comboNumText = new Text(420, 560, Integer.toString(maxCombo) + "x");
        comboNumText.setFill(Color.WHITE);
        comboNumText.setStyle("-fx-font: 50 verdana;");
        comboNumText.setStroke(Color.BLACK);
        comboNumText.setStrokeWidth(0.5);
        comboNumText.setOpacity(0);
        getChildren().add(comboNumText);

        clauseText = new Text(600, 500, maxClause);
        clauseText.setFill(Color.WHITE);
        clauseText.setStyle("-fx-font: 50 verdana;");
        clauseText.setEffect(new DropShadow(10, 0, 0, Color.PINK));
        clauseText.setStroke(Color.PINK);
        clauseText.setStrokeWidth(0.5);
        clauseText.setOpacity(0);
        getChildren().add(clauseText);

        rankingText = new Text(920, 100, "Ranking");
        rankingText.setStyle("-fx-font: 80 verdana;");
        Stop[] stops = new Stop[] { new Stop(0.5, Color.SILVER), new Stop(1, Color.BLACK) };
        LinearGradient lg1 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        rankingText.setFill(lg1);
        getChildren().add(rankingText);

        gradeText = new Text(920, 475, grade);
        gradeText.setFont(Font.font("Verdana", FontPosture.ITALIC, 400));
        Stop[] stops2 = new Stop[] { new Stop(0.5, calculateGradeColor()), new Stop(1, Color.DARKGREY) };
        LinearGradient lg2 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops2);
        gradeText.setEffect(new DropShadow(30, 0, 0, Color.BLACK));
        gradeText.setFill(lg2);
        gradeText.setStroke(Color.WHITE);
        gradeText.setStrokeWidth(5);
        gradeText.setOpacity(0);
        getChildren().add(gradeText);

        MenuButton levelSelectButton = new MenuButton();
        levelSelectButton.setText("Return To Level Select");
        levelSelectButton.setLayoutX(100);
        levelSelectButton.setLayoutY(600);
        this.getChildren().add(levelSelectButton);

        levelSelectButton.setOnAction(event -> {
            Scene levelSelectScene = new Scene(new LevelSelect(), 1250, 650);
            Stage Stage = (Stage) this.getScene().getWindow();
            Stage.setScene(levelSelectScene);
        });

        double transitionTime = 600;

        Timeline thumpTimeline = new Timeline(new KeyFrame(Duration.millis(transitionTime * 1.5), event -> {
            thumpsound();
            System.out.println("thump");
        }));
        thumpTimeline.setCycleCount(6);
        thumpTimeline.play();

        PauseTransition pause = new PauseTransition(Duration.millis(transitionTime / 2));

        ScaleTransition accuracyScale = new ScaleTransition(Duration.millis(transitionTime), accuracyNumText);
        accuracyScale.setFromX(10);
        accuracyScale.setFromY(10);
        accuracyScale.setToX(1);
        accuracyScale.setToY(1);

        FadeTransition accuracyFade = new FadeTransition(Duration.millis(transitionTime), accuracyNumText);
        accuracyFade.setFromValue(0);
        accuracyFade.setToValue(1);

        ParallelTransition accuracyParallel = new ParallelTransition(accuracyScale, accuracyFade);
        PauseTransition pause2 = new PauseTransition(Duration.millis(transitionTime / 2));

        ScaleTransition hitScale = new ScaleTransition(Duration.millis(transitionTime), hitNumText);
        hitScale.setFromX(10);
        hitScale.setFromY(10);
        hitScale.setToX(1);
        hitScale.setToY(1);

        FadeTransition hitFade = new FadeTransition(Duration.millis(transitionTime), hitNumText);
        hitFade.setFromValue(0);
        hitFade.setToValue(1);

        ParallelTransition hitParallel = new ParallelTransition(hitScale, hitFade);
        PauseTransition pause3 = new PauseTransition(Duration.millis(transitionTime / 2));

        ScaleTransition missScale = new ScaleTransition(Duration.millis(transitionTime), missNumText);
        missScale.setFromX(10);
        missScale.setFromY(10);
        missScale.setToX(1);
        missScale.setToY(1);

        FadeTransition missFade = new FadeTransition(Duration.millis(transitionTime), missNumText);
        missFade.setFromValue(0);
        missFade.setToValue(1);

        ParallelTransition missParallel = new ParallelTransition(missScale, missFade);
        PauseTransition pause4 = new PauseTransition(Duration.millis(transitionTime / 2));

        ScaleTransition comboScale = new ScaleTransition(Duration.millis(transitionTime), comboNumText);
        comboScale.setFromX(10);
        comboScale.setFromY(10);
        comboScale.setToX(1);
        comboScale.setToY(1);

        FadeTransition comboFade = new FadeTransition(Duration.millis(transitionTime), comboNumText);
        comboFade.setFromValue(0);
        comboFade.setToValue(1);

        ParallelTransition comboParallel = new ParallelTransition(comboScale, comboFade);
        PauseTransition pause5 = new PauseTransition(Duration.millis(transitionTime / 2));

        ScaleTransition clauseScale = new ScaleTransition(Duration.millis(transitionTime), clauseText);
        clauseScale.setFromX(10);
        clauseScale.setFromY(10);
        clauseScale.setToX(1);
        clauseScale.setToY(1);

        FadeTransition clauseFade = new FadeTransition(Duration.millis(transitionTime), clauseText);
        clauseFade.setFromValue(0);
        clauseFade.setToValue(1);

        ParallelTransition clauseParallel = new ParallelTransition(clauseScale, clauseFade);
        PauseTransition pause6 = new PauseTransition(Duration.millis(transitionTime / 2));

        ScaleTransition gradeScale = new ScaleTransition(Duration.millis(transitionTime), gradeText);
        gradeScale.setFromX(10);
        gradeScale.setFromY(10);
        gradeScale.setToX(1);
        gradeScale.setToY(1);

        FadeTransition gradeFade = new FadeTransition(Duration.millis(transitionTime), gradeText);
        gradeFade.setFromValue(0);
        gradeFade.setToValue(1);

        ParallelTransition gradeParallel = new ParallelTransition(gradeScale, gradeFade);

        SequentialTransition sequentialTransition = new SequentialTransition(pause, accuracyParallel, pause2,
                hitParallel, pause3, missParallel, pause4, comboParallel, pause5, clauseParallel, pause6,
                gradeParallel);
        sequentialTransition.play();

        sequentialTransition.setOnFinished(event -> playGradeSound());

    }

    private String calculateGrade() {
        if (accuracy >= 95) {
            return "S";
        } else if (accuracy >= 90) {
            return "A";
        } else if (accuracy >= 80) {
            return "B";
        } else if (accuracy >= 70) {
            return "C";
        } else if (accuracy >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    private Color calculateGradeColor() {
        if (accuracy >= 95) {
            return Color.GOLD;
        } else if (accuracy >= 90) {
            return Color.LIMEGREEN;
        } else if (accuracy >= 80) {
            return Color.MEDIUMBLUE;
        } else if (accuracy >= 70) {
            return Color.ORCHID;
        } else if (accuracy >= 60) {
            return Color.CRIMSON;
        } else {
            return Color.GREY;
        }
    }

    private String calculateClause(int maxClauseIndex) {
        String[] comboLevelTexts = { "NO\n CLAUSE", "FIRST\n CLAUSE", "SECOND\n CLAUSE", "THIRD\n CLAUSE",
                "FOURTH\n CLAUSE",
                "FIFTH\n CLAUSE", "SIXTH\n CLAUSE", "SEVENTH\n CLAUSE", "EIGHTH\n CLAUSE", "NINTH\n CLAUSE",
                "FINAL\n CLAUSE" };
        return comboLevelTexts[maxClauseIndex];

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

    public void thumpsound() {
        String s = "src/main/resources/thump.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        MediaPlayer thumpPlayer = new MediaPlayer(h);
        thumpPlayer.setVolume(10 * GamePane.getVolumeLevel());
        thumpPlayer.play();
        // SOMETIMES THUMP WONT PLAY IF I DONT CONSTANTLY CHECK THAT IT EXISTS
        Timeline checkThump = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            thumpPlayer.getStatus();
        }));
        checkThump.setCycleCount(Timeline.INDEFINITE);
        checkThump.play();
    }

    public void hitsound() {
        String s = "src/main/resources/hitsound.wav";
        Media h = new Media(Paths.get(s).toUri().toString());
        MediaPlayer hitPlayer = new MediaPlayer(h);
        hitPlayer.setVolume(0.5 * GamePane.getVolumeLevel());
        hitPlayer.play();
    }

    public void playGradeSound() {
        if (accuracy >= 95) {
            playApplause();
        } else if (accuracy >= 90) {
            playApplause();
        } else if (accuracy >= 80) {
            playGoodSound();
        } else if (accuracy >= 70) {
            playGoodSound();
        } else if (accuracy >= 60) {
            playFailSound();
        } else {
            playFailSound();
        }
    }

    public void playApplause() {
        String s = "src/main/resources/applause.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        MediaPlayer applausePlayer = new MediaPlayer(h);
        applausePlayer.setVolume(0.5 * GamePane.getVolumeLevel());
        applausePlayer.play();
        Timeline checkApplause = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            applausePlayer.getStatus();
        }));
        checkApplause.setCycleCount(Timeline.INDEFINITE);
        checkApplause.play();
    }

    public void playGoodSound() {
        String s = "src/main/resources/goodSound.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        MediaPlayer goodPlayer = new MediaPlayer(h);
        goodPlayer.setVolume(2 * GamePane.getVolumeLevel());
        goodPlayer.play();
        Timeline checkGood = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            goodPlayer.getStatus();
        }));
        checkGood.setCycleCount(Timeline.INDEFINITE);
        checkGood.play();
    }

    public void playFailSound() {
        String s = "src/main/resources/failSound.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        MediaPlayer failPlayer = new MediaPlayer(h);
        failPlayer.setVolume(1 * GamePane.getVolumeLevel());
        failPlayer.play();
        Timeline checkFail = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            failPlayer.getStatus();
        }));
        checkFail.setCycleCount(Timeline.INDEFINITE);
        checkFail.play();
    }

}
