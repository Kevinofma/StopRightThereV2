package edu.guilford;

import java.lang.NullPointerException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import gameassets.AccuracyLabel;
import gameassets.CountdownText;
import gameassets.GameLine;
import gameassets.HealthBar;
import gameassets.MissButton;
import gameassets.NoteButton;
import gameassets.PauseMenuButton;
import gameassets.PausedHintText;
import gameassets.PausedText;
import gameassets.ScoreLabel;
import gameassets.TimeRemainingBar;
import gameassets.VolumeLabel;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

// FINISHED FEATURES:
// * Play music in the background
// * Create a circular nice looking button that spawns on the screen
// * Make the button spawn on the beat of the song at a random location
// * button now spawns before the beat and fades in and grows in size
// * Make the button disappear after the beat of the song
// * button now fades out and shrinks in size
// * Make the number on the button increase by 1 everytime and resets when it gets to high
// * Add a label on the top right that tracks the score
// * place a transparent video in the background
// * add a start delay so the player and song can get ready before the first button spawn
// * Use System.getCurrentTimeMillis() to calculate when the button is clicked
// * give the player points based on when the button is clicked
// * Fix Bug where music would some times stop randomly
// * Add a checkmark visual when the button is clicked on the beat
// * Add a Xmark visual when the button is clicked too early or too late, or when the button despawns without being clicked
// * create a combo counter system that keeps track of how many times the player clicks the button on the beat in a row
// * create a comboLevel system that rewards getting a combo of multiples of 10
// * create visuals for the combo counter and when the comboLevel increases
// * display an image of the comboLevel reward when the comboLevel increases // SCRAPPED
// * make the image slide across the screen when the comboLevel increases, farther from the center, faster the slide speed // SCRAPPED
// * play sound when the comboLevel increases
// * fix a bug where the if combo level increases past 10, the comboLevel code breaks
// * create a line when the next button spawns that connects the previous button to the next button
// * make the game buttons change color when the number of the button resets back to 1
// * ACCIDENTIAL FEATURE: chaos game mode where the next button spawns from the previous button and moves to the next random location
// * create a health bar: so that you cant just leave the game running and get a really high negative score
// * health bar is at the top of the screen
// * health decreases over time and when decreases when the player misses a note
// * health increases when the player clicks a note on the beat
// * health bar is green when full, yellow when half, and red when low
// * create a countdown system that counts down the beats until the actual game starts
// * display a large text of the number of beats left
// * pause the game when the player presses the esc key
// create a pause menu that has a restart button and return to level select button
// display a pause Text in the top middle of the screen
// dim the background
// stop all the timers, music, threads, etc
// create a rotating text similar to splash text from minecraft that tells you how to unpause
// create a way to unpause the game
// * create a way to restart the game
// * create a way to return to the level select screen
// * create a way for the game to end when you lose when the health reaches 0
// * create a way for the game to end when the song ends
// * BUG FIX: pausing the game when the countdown reaches 1 breaks the game
// TEMP FIX: catch the null pointer exception that happens when the countdown reaches 1 and restart the level
// * BUG FIX: restarting the level when some of the timelines haven't started yet breaks the game
// * optimize the code so that anything that can but put into a class that extends the javafx object class is put into a class that does so Ex: text, button, rectangle, etc
// idk if this actually optimizes the code but it makes it look cleaner and shows usage of extends
// have musicplayer threads take the path of the song as a parameter // SCRAPPED CANT MAKE SUBCLASSES FROM MUSICPLAYER
// move all these classes into a package called gameassets
// * change the volume of the music and soundFX when paused
// * set the score to 0 if the score is negative so you cant get a negative score
// * add a accuracy counter that keeps track of the amount of times the player clicked the button on the beat and the amount of times the player missed the beat as a percent
// * add time left until the song is over as a pie chart // pie chart is confusing to implement use a vertical bar instead
// * End the game when the song ends
// * create a hardcore mode where the game ends as soon as the player misses the beat
// ** Made variables in GamePane editable from LevelSelect so it is easy to create new Levels
// * Allow the song to come to the video
// * BUG FIX: volume slider was incorrect when song came from video
// * BUG FIX: If countdown is a 2 digit number, move it slightly to the left to keep it centered
// * BUG FIX: Move score, accuracy, and time remaining bar above the video so it isn't transparent
// * BUG FIX: you are sometimes unable to click a button under the checkmark or Xmark
// * Now spawns a Xmark when you click outside of the button

// TO-DO LIST: (copy and paste into features when finished)

// TO - MAYBE - DO LIST:
// and more splash text maybe?
// replace the start button with a cool animation when hovered
// create a formula that gets where you clicked on the button and calculate how close you were to the center of the button, and store it as a value

public class GamePane extends Pane {

    String cssColor = "pink"; // always start buttons as pink
    static boolean switchColor = false;
    private Button previousButton = null; // used to store the values of the previous button so that a line can be drawn
                                          // from the previous button to the next button
    ArrayList<Line> lines = new ArrayList<>(); // List to store the line objects because theres a bug where the first
                                               // line
    // created wont clear so we clear the list instead of each line individually
    private GameLine line;
    ArrayList<PathTransition> transitions = new ArrayList<>(); // List to store the path transitions
    private ScoreLabel scoreLabel;
    double bpm = LevelSelect.getBPM(); // beats per minute of the song
    double beatDuration = (60000 / bpm); // duration of a single beat in milliseconds
    double noteBuffer = 500 * ((double) bpm / 100); // the amount of time before the beat that the button spawns
    int beatsToDelay = LevelSelect.getSongDelay();
    int countdownValue = beatsToDelay + 1;
    private CountdownText countdownText;
    int buttonCount = 1;
    boolean clicked = false;
    int score = 0;
    int comboCounter = 0;
    int comboLevel = 0;
    private static final double HEALTH_BAR_WIDTH = 1250.0;
    private static final double HEALTH_BAR_HEIGHT = 20.0;

    private double currentHealth = 100.0;
    private HealthBar healthBar;

    private boolean hardcoreDiff = false;
    private boolean chaosDiff = false;
    private boolean infiniteHealth = false;

    private static Timeline countdownTimeline;

    private boolean isGamePaused = false;
    private boolean pausedDuringStartup = false;
    Animation healthTimeline;
    Timeline gameLoop1;
    Timeline lateTimer;
    Timeline clearTimer;
    MediaPlayer musicPlayer;
    MediaPlayer comboPlayer;
    boolean comboPlayerOn = false; // use for the checkMusicPlayer method to only check the comboPlayer when its
                                   // been turned on
    MediaPlayer mediaVideoPlayer;
    PauseTransition delay;

    private PauseMenuButton levelSelectButton;
    private PauseMenuButton restartButton;
    private PausedText pausedText;
    private Rectangle dimRectangle;
    private PausedHintText pauseHint;

    private double volumeLevel = 1;
    private VolumeLabel volumeLabel;
    private Slider volumeSlider;

    int totalNumButtons = 0;
    int numButtonsHit = 0;
    int numButtonsMissed = 0;
    double accuracy = 0;
    private AccuracyLabel accuracyLabel;

    private double remainingTime; // (in seconds)
    private static double totalSongTime = 100; // random high value (in seconds)
    private TimeRemainingBar timeBar;
    private int timeBarHeight = 575;
    private int refreshRate = 15;
    Timeline barTimeline;

    private boolean songWithVideo = LevelSelect.getSongWithVideo();

    // constructor
    public GamePane(String songVideo, String songFileName) {

        hardcoreDiff = LevelSelect.getHardcoreDiff();
        chaosDiff = LevelSelect.getChaosDiff();
        infiniteHealth = LevelSelect.getInfiniteHealth();

        // set the background color to black
        setStyle("-fx-background-color: black;");

        // pause the game when the user presses the escape key
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                if (isGamePaused == false) {
                    pauseGame();

                    dimRectangle = new Rectangle(0, 0, 1280, 720);
                    dimRectangle.setFill(Color.rgb(0, 0, 0, 0.9));
                    getChildren().add(dimRectangle);

                    levelSelectButton = new PauseMenuButton();
                    levelSelectButton.setText("Level Select");
                    levelSelectButton.setTranslateX(475);
                    levelSelectButton.setTranslateY(275);
                    getChildren().add(levelSelectButton);
                    levelSelectButton.setOnAction(event -> {
                        hitsound();
                        endGameThreads();
                        Scene levelSelectScene = new Scene(new LevelSelect(), 585, 360);
                        Stage Stage = (Stage) this.getScene().getWindow();
                        Stage.setScene(levelSelectScene);
                    });

                    restartButton = new PauseMenuButton();
                    restartButton.setText("Restart Level");
                    restartButton.setTranslateX(465);
                    restartButton.setTranslateY(425);
                    getChildren().add(restartButton);
                    restartButton.setOnAction(event -> {
                        hitsound();
                        restartGame();
                    });

                    pausedText = new PausedText();
                    pausedText.setText("Paused");
                    pausedText.setTranslateX(405);
                    pausedText.setTranslateY(200);
                    getChildren().add(pausedText);

                    pauseHint = new PausedHintText();
                    pauseHint.setText("Press ESC to unpause");
                    pauseHint.setTranslateX(50);
                    pauseHint.setTranslateY(250);
                    getChildren().add(pauseHint);
                    // Tilt the text at a 45 degree angle and give it animation that changes the
                    // angle by -15 to 15 degrees
                    // every 2 seconds
                    RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), pauseHint);
                    rotateTransition.setFromAngle(-30);
                    rotateTransition.setToAngle(30);
                    rotateTransition.setCycleCount(Animation.INDEFINITE);
                    rotateTransition.setAutoReverse(true);
                    rotateTransition.play();

                    // create a slider that controls the volume of the music
                    volumeSlider = new Slider(0, 1, 1); // Volume range is between 0 and 1
                    volumeSlider.setOrientation(Orientation.HORIZONTAL);
                    volumeSlider.setLayoutX(460); // Adjust the position as needed
                    volumeSlider.setLayoutY(600);
                    volumeSlider.setValue(volumeLevel);
                    volumeSlider.setPrefWidth(350);
                    getChildren().add(volumeSlider);
                    volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                        volumeLevel = newValue.doubleValue();
                    });

                    volumeLabel = new VolumeLabel();
                    volumeLabel.setText("Volume");
                    volumeLabel.setLayoutX(600); // Adjust the position as needed
                    volumeLabel.setLayoutY(585);
                    getChildren().add(volumeLabel);

                } else {
                    unpauseGame();
                    getChildren().remove(levelSelectButton);
                    getChildren().remove(pausedText);
                    getChildren().remove(dimRectangle);
                    getChildren().remove(restartButton);
                    getChildren().remove(pauseHint);
                    getChildren().remove(volumeSlider);
                    getChildren().remove(volumeLabel);
                }
            }
        });

        healthBar = new HealthBar();
        healthBar.setWidth(HEALTH_BAR_WIDTH);
        healthBar.setHeight(HEALTH_BAR_HEIGHT);
        healthBar.setFill(Color.WHITE);
        getChildren().add(healthBar);
        double decreaseAmount = 0.1;
        int durationMillis = 15;
        healthTimeline = new Timeline(
                new javafx.animation.KeyFrame(javafx.util.Duration.millis(durationMillis), event -> {
                    currentHealth -= decreaseAmount;
                    updateHealthBar();
                }));
        healthTimeline.setCycleCount(javafx.animation.Animation.INDEFINITE);

        Media mediaVideo = new Media(Paths.get("src/main/resources/" + songVideo).toUri().toString());
        mediaVideoPlayer = new MediaPlayer(mediaVideo);
        MediaView mediaVideoView = new MediaView(mediaVideoPlayer);
        mediaVideoView.setOpacity(0.4);
        mediaVideoPlayer.setAutoPlay(true);
        mediaVideoPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        if (songWithVideo == false) {
            mediaVideoPlayer.setVolume(0);
        } else if (songWithVideo == true) {
            mediaVideoPlayer.setVolume(1 * volumeLevel);
        }
        mediaVideoView.setFitWidth(1250);
        mediaVideoView.setFitHeight(750);
        mediaVideoPlayer.play();
        getChildren().add(mediaVideoView);

        timeBar = new TimeRemainingBar();
        timeBar.setWidth(20);
        timeBar.setHeight(timeBarHeight);
        // position the time bar on the right side of the screen
        timeBar.setTranslateX(1200);
        timeBar.setTranslateY(40);
        getChildren().add(timeBar);

        scoreLabel = new ScoreLabel();
        scoreLabel.setScore(score);
        scoreLabel.setTranslateX(1000);
        scoreLabel.setTranslateY(30);
        getChildren().add(scoreLabel);

        // create a an accuracy label that displays the accuracy of the player
        accuracyLabel = new AccuracyLabel();
        accuracyLabel.setAccuracy(100);
        accuracyLabel.setTranslateX(1000);
        accuracyLabel.setTranslateY(60);
        getChildren().add(accuracyLabel);

        countdownText = new CountdownText();
        countdownText.setTranslateX(550);
        countdownText.setTranslateY(500);
        getChildren().add(countdownText);
        startCountdown();

        Button missButton = new MissButton();
        missButton.setPrefSize(1250, 750);
        getChildren().add(missButton);
        missButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                decreaseScoreLight();
                ImageView XMark = new ImageView(new Image("xmark.png"));
                XMark.setTranslateX(event.getX() - 75);
                XMark.setTranslateY(event.getY() - 37.5);
                XMark.setMouseTransparent(true);
                XMark.setFitWidth(150);
                XMark.setFitHeight(75);
                XMark.setOpacity(1);
                getChildren().add(XMark);
                // create a parallel transition to fade out the Xmark and scale it down to 0
                ParallelTransition fadeOut = new ParallelTransition();
                FadeTransition fade = new FadeTransition(Duration.millis(2000), XMark);
                fade.setToValue(0);
                ScaleTransition scale = new ScaleTransition(Duration.millis(2000), XMark);
                scale.setToX(0);
                scale.setToY(0);
                fadeOut.getChildren().addAll(fade, scale);
                fadeOut.play();
                misssound();
            }
        });

        Media musicMedia = new Media(Paths.get("src/main/resources/" + songFileName).toUri().toString());
        musicPlayer = new MediaPlayer(musicMedia);
        if (songWithVideo == false) {
            musicPlayer.setVolume(0.5 * volumeLevel);
        } else if (songWithVideo == true) {
            musicPlayer.setVolume(0);
        }
        Timeline startMusic = new Timeline(new KeyFrame(Duration.millis(noteBuffer), e -> {
            musicPlayer.play();
        }));
        startMusic.play();
        musicPlayer.setOnReady(() -> {
            if (musicPlayer.getStatus() == MediaPlayer.Status.READY) {
                System.out.println("Music is ready");
                Duration totalDuration = musicPlayer.getMedia().getDuration();
                totalSongTime = totalDuration.toSeconds();
                System.out.println("Total song time: " + totalSongTime + " seconds");
                remainingTime = totalSongTime;
            }
        });
        startBarTimer();

        gameLoop1 = new Timeline(new KeyFrame(Duration.millis(beatDuration), event -> {
            // create a new button and add it to the scene
            Button gameButton = new NoteButton();
            // PLACE THE BUTTON AT A RANDOM LOCATION ON THE SCREEN
            gameButton.setTranslateX(Math.random() * 1000 + 25);
            gameButton.setTranslateY(Math.random() * 500 + 25);
            switchColor = GamePane.getSwitchColor();
            if (switchColor == true) {
                cssColor = getRandomCSSColor();
                switchColor = false;
                // set the switchColor variable in Level1 to false
                GamePane.setSwitchColor(switchColor);
            }
            gameButton.setStyle("-fx-background-color: " + cssColor + ";" +
                    "-fx-background-radius: 50; " +
                    "-fx-border-color: white; " +
                    "-fx-border-width: 5px; " +
                    "-fx-border-radius: 50; " +
                    "-fx-text-fill: white;");
            // change the text of the button to the number of the button
            gameButton.setText(Integer.toString(buttonCount));
            // increment the button count every time a button is spawned but reset the count
            // to 1 when it goes above 10
            buttonCount++;
            totalNumButtons++;
            if (buttonCount > 4) {
                buttonCount = 1;
                switchColor = true;
            }

            // accidently created this monster of a gamemode attempting to draw animated
            // line, chaos mode
            if (previousButton != null && chaosDiff == true) {
                PathTransition transition = new PathTransition();
                transition.setNode(gameButton);
                transition.setDuration(Duration.seconds(1));
                transition.setPath(new Line(previousButton.getTranslateX() + previousButton.getWidth() / 2,
                        previousButton.getTranslateY() + previousButton.getHeight() / 2,
                        gameButton.getTranslateX() + gameButton.getWidth() / 2,
                        gameButton.getTranslateY() + gameButton.getHeight() / 2));
                transitions.add(transition); // Add the transition to the list
                transition.play(); // Start the transition
            }

            // Draw a line from the previous button to the current button
            if (previousButton != null && chaosDiff == false) {
                clearLines();
                line = new GameLine();
                line.setMouseTransparent(true);
                line.setStartX(previousButton.getTranslateX() + previousButton.getWidth() / 2);
                line.setStartY(previousButton.getTranslateY() + previousButton.getHeight() / 2);
                line.setEndX(gameButton.getTranslateX() + gameButton.getWidth() / 2 + 50);
                line.setEndY(gameButton.getTranslateY() + gameButton.getHeight() / 2 + 50);
                lines.add(line); // Add the line to the list
                getChildren().add(line);

            }

            // Store the current button as the previous button for the next iteration
            previousButton = gameButton;

            getChildren().add(gameButton);
            long spawnTime = System.currentTimeMillis();

            // make the button increase the score when it is clicked
            gameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    clicked = true;
                    // get the current time when the button is clicked
                    long clickTime = System.currentTimeMillis();
                    // calculate the difference between the current time and the time when the
                    // button was spawned
                    long elapsedTime = clickTime - spawnTime;
                    System.out.println(elapsedTime);
                    // if the difference is within the beat duration, then the player clicked the
                    // button on the beat
                    if (elapsedTime < 1100 && elapsedTime > 600) {
                        // remove the button from the scene
                        getChildren().remove(gameButton);
                        // add a checkmark image to the location of the button
                        ImageView checkMark = new ImageView(new Image("checkmark.png"));
                        checkMark.setTranslateX(gameButton.getTranslateX());
                        checkMark.setTranslateY(gameButton.getTranslateY());
                        checkMark.setMouseTransparent(true);
                        checkMark.setFitWidth(100);
                        checkMark.setFitHeight(100);
                        checkMark.setOpacity(1);
                        getChildren().add(checkMark);
                        // create a parallel transition to fade out the checkmark and scale it down to 0
                        ParallelTransition fadeOut = new ParallelTransition();
                        FadeTransition fade = new FadeTransition(Duration.millis(1000), checkMark);
                        fade.setToValue(0);
                        ScaleTransition scale = new ScaleTransition(Duration.millis(1000), checkMark);
                        scale.setToX(0);
                        scale.setToY(0);
                        fadeOut.getChildren().addAll(fade, scale);
                        fadeOut.play();
                        // increase the score
                        increaseScore();
                        System.out.println("Perfect");
                        hitsound();

                        // if comboCounter is greater than 0, then display the combo counter
                        if (comboCounter > 0) {
                            Text comboText = new Text();
                            comboText.setMouseTransparent(true);
                            comboText.setFont(Font.font("Impact", 20 + comboCounter));
                            comboText.setFill(Color.WHITE);
                            // give the text a thick pink border
                            comboText.setStroke(Color.PINK);
                            comboText.setStrokeWidth(3);
                            // put the combo text on the top left corner of the screen
                            comboText.setTranslateX(75);
                            comboText.setTranslateY(125);
                            comboText.setOpacity(1);
                            comboText.setText("Combo x" + comboCounter);
                            // give the comboText a random tilt from -30 to 30 degrees
                            comboText.setRotate(Math.random() * 60 - 30);
                            getChildren().add(comboText);
                            // create a parallel transition to fade out the combo text and scale it down to
                            // 0
                            ParallelTransition fadeOutCombo = new ParallelTransition();
                            FadeTransition fadeCombo = new FadeTransition(Duration.millis(2000), comboText);
                            fadeCombo.setToValue(0);
                            ScaleTransition scaleCombo = new ScaleTransition(Duration.millis(2000), comboText);
                            scaleCombo.setToX(0);
                            scaleCombo.setToY(0);
                            fadeOutCombo.getChildren().addAll(fadeCombo, scaleCombo);
                            fadeOutCombo.play();
                        }

                        // at every increment of 10 combo, increase the combo level
                        if (comboCounter >= 10 && comboCounter % 10 == 0) {
                            // if (comboCounter >= 0) {

                            comboLevel++;
                            if (comboLevel == 11) {
                                comboLevel = 1;
                            }
                            combosound();

                            Text comboLevelText = new Text();
                            comboLevelText.setMouseTransparent(true);
                            comboLevelText.setFont(Font.font("Impact", 56));
                            comboLevelText.setFill(Color.WHITE);
                            // give the text a thick pink border
                            comboLevelText.setStroke(Color.PINK);
                            comboLevelText.setStrokeWidth(3);

                            // place the combo Text at the center of the screen
                            comboLevelText.setTranslateX(425);
                            comboLevelText.setTranslateY(325);
                            comboLevelText.setOpacity(1);
                            getChildren().add(comboLevelText);

                            String[] comboLevelTexts = { "FIRST CLAUSE", "SECOND CLAUSE", "THIRD CLAUSE",
                                    "FOURTH CLAUSE",
                                    "FIFTH CLAUSE", "SIXTH CLAUSE", "SEVENTH CLAUSE", "EIGHTH CLAUSE", "NINTH CLAUSE",
                                    "FINAL CLAUSE" };

                            if (comboLevel >= 1 && comboLevel <= 10) {
                                comboLevelText.setText(comboLevelTexts[comboLevel - 1] + "   X " + comboCounter);

                                FadeTransition fadeComboLevelText = new FadeTransition(Duration.millis(1000),
                                        comboLevelText);
                                fadeComboLevelText.setToValue(0);
                                fadeComboLevelText.play();
                            }

                            // // Add a the combo image the buttons and fade it out
                            // ImageView comboImage = new ImageView(new Image("comboTestImage.png"));
                            // // set the location of the combo image in the center
                            // comboImage.setTranslateX(425);
                            // comboImage.setTranslateY(100);
                            // comboImage.setOpacity(1);
                            // comboImage.setMouseTransparent(true);
                            // // comboImage.setTranslateX(425);
                            // // comboImage.setTranslateY(100);
                            // comboImage.setOpacity(1);
                            // getChildren().add(comboImage);

                            // FadeTransition fadeCombo = new FadeTransition(Duration.millis(1000),
                            // comboImage);
                            // fadeCombo.setToValue(0);
                            // fadeCombo.play();

                        }
                    } else {
                        // remove the button from the scene
                        getChildren().remove(gameButton);
                        // Add a Xmark image to the location of the button
                        ImageView XMark = new ImageView(new Image("xmark.png"));
                        XMark.setTranslateX(gameButton.getTranslateX());
                        XMark.setTranslateY(gameButton.getTranslateY());
                        XMark.setMouseTransparent(true);
                        XMark.setFitWidth(150);
                        XMark.setFitHeight(75);
                        XMark.setOpacity(1);
                        getChildren().add(XMark);
                        // create a parallel transition to fade out the Xmark and scale it down to 0
                        ParallelTransition fadeOut = new ParallelTransition();
                        FadeTransition fade = new FadeTransition(Duration.millis(2000), XMark);
                        fade.setToValue(0);
                        ScaleTransition scale = new ScaleTransition(Duration.millis(2000), XMark);
                        scale.setToX(0);
                        scale.setToY(0);
                        fadeOut.getChildren().addAll(fade, scale);
                        fadeOut.play();
                        // decrease the score
                        decreaseScoreLight();
                        System.out.println("missed");
                    }
                }
            });

            // Create a FadeTransition to fade in the button over 1 second
            FadeTransition fadeIn = new FadeTransition(Duration.millis(noteBuffer), gameButton);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);

            // Create a ScaleTransition to scale the button up to twice its size over 0.5
            // seconds
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(noteBuffer), gameButton);
            scaleUp.setFromX(0);
            scaleUp.setFromY(0);
            scaleUp.setToX(1.0);
            scaleUp.setToY(1.0);

            // Add the transitions to a ParallelTransition to play them simultaneously
            ParallelTransition parallelTransition = new ParallelTransition(gameButton, fadeIn, scaleUp);

            // Start the transitions
            parallelTransition.play();

            // schedule the button to be removed after the beat
            lateTimer = new Timeline(new KeyFrame(Duration.millis(beatDuration * 2), e -> {
                // fade out the button over 0.5 seconds
                FadeTransition fadeOut = new FadeTransition(Duration.millis(noteBuffer), gameButton);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);

                // scale the button down to 0 over 0.5 seconds
                ScaleTransition scaleDown = new ScaleTransition(Duration.millis(noteBuffer), gameButton);
                scaleDown.setFromX(1.0);
                scaleDown.setFromY(1.0);
                scaleDown.setToX(0);
                scaleDown.setToY(0);

                // add the transitions to a ParallelTransition to play them simultaneously
                ParallelTransition parallelTransition2 = new ParallelTransition(gameButton, fadeOut, scaleDown);

                // start the transitions
                parallelTransition2.play();

            }));
            lateTimer.play();

            clearTimer = new Timeline(new KeyFrame(Duration.millis(beatDuration * 2 + noteBuffer), e -> {
                // Add a Xmark image to the location of the button
                if (!clicked) {
                    ImageView XMark = new ImageView(new Image("xmark.png"));
                    XMark.setTranslateX(gameButton.getTranslateX());
                    XMark.setTranslateY(gameButton.getTranslateY());
                    XMark.setFitWidth(150);
                    XMark.setFitHeight(75);
                    XMark.setOpacity(1);
                    getChildren().add(XMark);
                    // create a parallel transition to fade out the Xmark and scale it down to 0
                    ParallelTransition fadeOut = new ParallelTransition();
                    FadeTransition fade = new FadeTransition(Duration.millis(1000), XMark);
                    fade.setToValue(0);
                    ScaleTransition scale = new ScaleTransition(Duration.millis(1000), XMark);
                    scale.setToX(0);
                    scale.setToY(0);
                    fadeOut.getChildren().addAll(fade, scale);
                    fadeOut.play();
                    // decrease the score
                    decreaseScoreFull();
                    misssound();
                }
                clicked = false;
                getChildren().remove(gameButton);
            }));
            clearTimer.play();
            // check if the music is still playing
            checkMusicStatus();
            checkSongEnd();

        }));

        gameLoop1.setCycleCount(Timeline.INDEFINITE);

        double delayDuration = beatsToDelay * beatDuration;
        delay = new PauseTransition(Duration.millis(delayDuration));
        delay.setOnFinished(event -> {
            gameLoop1.play();
            if (!infiniteHealth) {
                healthTimeline.play(); // only play the health timeline if infinite health is not enabled
            }
        });
        delay.play();
        System.out.println(delay.getStatus());
    }

    private void startCountdown() {
        countdownTimeline = new Timeline(
                new KeyFrame(Duration.millis(beatDuration), event -> {
                    countdownValue--;
                    updateCountdownText();
                    if (countdownValue == 0) {
                        // Countdown is complete, stop the timeline
                        countdownTimeline.stop();
                        getChildren().remove(countdownText);
                        // Perform any desired actions after the countdown
                        System.out.println("Countdown complete");
                    }
                }));
        countdownTimeline.setCycleCount(Animation.INDEFINITE);
        countdownTimeline.play();
    }

    private void updateCountdownText() {
        if (countdownValue >= 10) {
            countdownText.setTranslateX(450);
            countdownText.setTranslateY(500);
        } else {
            countdownText.setTranslateX(550);
            countdownText.setTranslateY(500);
        }
        countdownText.setText(String.valueOf(countdownValue));
    }

    public void hitsound() {
        String s = "src/main/resources/hitsound.wav";
        Media h = new Media(Paths.get(s).toUri().toString());
        MediaPlayer hitPlayer = new MediaPlayer(h);
        // set the volume to 50%
        hitPlayer.setVolume(0.5 * volumeLevel);
        hitPlayer.play();
    }

    public void misssound() {
        String s = "src/main/resources/misssound.wav";
        Media h = new Media(Paths.get(s).toUri().toString());
        MediaPlayer missPlayer = new MediaPlayer(h);
        missPlayer.setVolume(1 * volumeLevel);
        // check to see if the missPlayer is already playing so that the miss sounds
        // dont buffer
        if (missPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            // if it is, stop it and play it again
            missPlayer.stop();
            missPlayer.play();
        } else {
            // if it isnt, just play it
            missPlayer.play();
        }
    }

    public void increaseScore() {
        score = score + 500;
        scoreLabel.setScore(score);
        currentHealth = currentHealth + 5;
        comboCounter++;
        numButtonsHit++;
        calculateAccuracy();
    }

    public void comboBonus() {
        score = score + 1000;
        scoreLabel.setScore(score);
    }

    public void decreaseScoreLight() {
        comboCounter = 0;
        score = score - 250;
        if (score == 0 || score < 0) {
            score = 0;
        }
        scoreLabel.setScore(score);
        comboCounter = 0;
        comboLevel = 0;
        currentHealth = currentHealth - 2;
        numButtonsMissed++;
        calculateAccuracy();
        if (hardcoreDiff) {
            endGame();
        }
    }

    public void decreaseScoreFull() {
        comboCounter = 0;
        score = score - 500;
        if (score == 0 || score < 0) {
            score = 0;
        }
        scoreLabel.setScore(score);
        comboCounter = 0;
        comboLevel = 0;
        currentHealth = currentHealth - 3;
        numButtonsMissed++;
        calculateAccuracy();
        if (hardcoreDiff) {
            endGame();
        }
    }

    public void calculateAccuracy() {
        // accuracy = ((double) numButtonsHit / totalNumButtons) * 100;
        accuracy = ((double) numButtonsHit / (numButtonsHit + numButtonsMissed)) * 100;
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        double roundedAccuracy = Double.parseDouble(decimalFormat.format(accuracy));
        accuracyLabel.setAccuracy(roundedAccuracy);
    }

    public void combosound() {
        String s = "src/main/resources/Round" + comboLevel + ".wav";
        Media h = new Media(Paths.get(s).toUri().toString());
        comboPlayer = new MediaPlayer(h);
        // set the volume to 50%
        comboPlayer.setVolume(1 * volumeLevel);
        comboPlayer.play();
        System.out.println(comboPlayer.getStatus());
        comboPlayerOn = true;

    }

    private void updateHealthBar() {
        // Update the health bar width based on the current health level
        double healthPercentage = currentHealth / 100.0;
        double newWidth = HEALTH_BAR_WIDTH * healthPercentage;
        healthBar.setWidth(newWidth);

        // Change the color of the health bar based on the health level
        if (healthPercentage >= 0.5) {
            healthBar.setFill(Color.WHITE);
        } else if (healthPercentage >= 0.2) {
            healthBar.setFill(Color.PINK);
        } else {
            healthBar.setFill(Color.RED);
        }

        // Check if health reaches 0 and trigger endGame()
        if (currentHealth <= 0) {
            endGame();
        }
    }

    private void clearLines() {
        getChildren().removeAll(lines);
        lines.clear();
    }

    // getter for switchColor
    public static boolean getSwitchColor() {
        return switchColor;
    }

    // setter for switchColor
    public static void setSwitchColor(boolean switchColor) {
        GamePane.switchColor = switchColor;
    }

    public static String getRandomCSSColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return String.format("#%02x%02x%02x", r, g, b);
    }

    // METHODS FOR PAUSING THE GAME
    public void pauseGame() {
        if (!isGamePaused) {
            try {
                if (delay.getStatus() == Status.RUNNING) {
                    delay.pause();
                    countdownTimeline.pause();
                    pausedDuringStartup = true;
                } else if (delay.getStatus() == Status.STOPPED) {
                    healthTimeline.pause();
                    gameLoop1.pause();
                    lateTimer.pause();
                    clearTimer.pause();
                    barTimeline.pause();
                }
                musicPlayer.pause();
                mediaVideoPlayer.pause();

                isGamePaused = true;
            } catch (NullPointerException e) {
                restartGame();
                System.err.println("Error pausing game " + e.getMessage());
            }
        }
    }

    public void unpauseGame() {
        if (isGamePaused) {
            if (pausedDuringStartup == true) {
                delay.play();
                countdownTimeline.play();
            } else if (pausedDuringStartup == false) {
                healthTimeline.play();
                gameLoop1.play();
                lateTimer.play();
                clearTimer.play();
                barTimeline.play();
            }
            musicPlayer.play();
            mediaVideoPlayer.play();
            changeVolume();
            pausedDuringStartup = false;
            isGamePaused = false;
        }
    }

    public void endGameThreads() {
        // Stop healthTimeline if running
        if (healthTimeline != null && healthTimeline.getStatus() == Animation.Status.RUNNING) {
            // checks to see if it even exists first and then checks if its running
            healthTimeline.stop();
        }

        // Stop barTimeline if running
        if (barTimeline != null && barTimeline.getStatus() == Animation.Status.RUNNING) {
            barTimeline.stop();
        }

        // Stop gameLoop1 if running
        if (gameLoop1 != null && gameLoop1.getStatus() == Animation.Status.RUNNING) {
            gameLoop1.stop();
        }

        // Stop lateTimer if running
        if (lateTimer != null && lateTimer.getStatus() == Animation.Status.RUNNING) {
            lateTimer.stop();
        }

        // Stop clearTimer if running
        if (clearTimer != null && clearTimer.getStatus() == Animation.Status.RUNNING) {
            clearTimer.stop();
        }

        // Stop delay if running
        if (delay != null && delay.getStatus() == Animation.Status.RUNNING) {
            delay.stop();
        }

        // Stop countdownTimeline if running
        if (countdownTimeline != null && countdownTimeline.getStatus() == Animation.Status.RUNNING) {
            countdownTimeline.stop();
        }

        // Stop musicPlayer if playing
        if (musicPlayer != null && musicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            musicPlayer.stop();
        }

        // Stop mediaVideoPlayer if playing
        if (mediaVideoPlayer != null && mediaVideoPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaVideoPlayer.stop();
        }

    }

    public void checkMusicStatus() {

        if (musicPlayer.getStatus() == MediaPlayer.Status.STOPPED) {
            endGame();
        }
        System.out.println(musicPlayer.getStatus()); // for some reason music will randomly stop sometimes if this
                                                     // is not here... WHYYYY!!!!

        if (comboPlayerOn) {
            System.out.println(comboPlayer.getStatus()); // for some reason music will randomly stop sometimes if this
                                                         // is not here... WHYYYY!!!!
        }
    }

    private void endGame() {
        endGameThreads();
        // place holder scene for the score card screen
        Scene infoScene = new Scene(new Tutorial(), 640, 480);
        Stage Stage = (Stage) this.getScene().getWindow();
        Stage.setScene(infoScene);
    }

    // create a restart game method
    public void restartGame() {
        endGameThreads();
        Scene level1Scene = new Scene(new GamePane(LevelSelect.getSongVideo(), LevelSelect.getSongFileName()), 1250,
                650);
        Stage Stage = (Stage) this.getScene().getWindow();
        Stage.setScene(level1Scene);
    }

    // create a method that changes the volumes of the music and the combo sounds
    public void changeVolume() {
        if (songWithVideo == true) {
            mediaVideoPlayer.setVolume(1 * volumeLevel);
        } else if (songWithVideo == false) {
            musicPlayer.setVolume(0.5 * volumeLevel);
        }
    }

    private void startBarTimer() {
        barTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> updateBar()),
                new KeyFrame(Duration.millis(refreshRate)));
        barTimeline.setCycleCount(Animation.INDEFINITE);
        barTimeline.play();
    }

    private void updateBar() {
        remainingTime = remainingTime - (refreshRate / 1000.0);
        double percentage = remainingTime / totalSongTime;
        timeBar.setHeight(timeBarHeight * percentage);
    }

    private void checkSongEnd() {
        if (remainingTime <= 0) {
            endGame();
        }
    }

}
