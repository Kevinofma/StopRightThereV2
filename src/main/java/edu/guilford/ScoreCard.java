package edu.guilford;

import javafx.scene.layout.Pane;

public class ScoreCard extends Pane {
    // attributes
    private int hits = GamePane.getNumHits();
    private int misses = GamePane.getNumMisses();
    private double accuracy = GamePane.getAccuracy();
    private int maxCombo = GamePane.getMaxCombo();
    private int maxClause = GamePane.getMaxClause();
    private int score = GamePane.getScore();
    private String grade = calculateGrade();
    private String songName = LevelSelect.getSongName();
    private String difficulty = LevelSelect.getDifficulty();
    private int starRating = LevelSelect.getDifficultyStars();
    private String mods = LevelSelect.getMods();

    // constructor
    public ScoreCard() {
        System.out.println("hits: " + hits);
        System.out.println("misses: " + misses);
        System.out.println("accuracy: " + accuracy);
        System.out.println("maxCombo: " + maxCombo);
        System.out.println("maxClause: " + maxClause);
        System.out.println("score: " + score);
        System.out.println("grade: " + grade);
        System.out.println("songName: " + songName);
        System.out.println("difficulty: " + difficulty);
        System.out.println("starRating: " + starRating);
        System.out.println("mods: " + mods);
    }

    private String calculateGrade() {
        return "F";
    }

}
