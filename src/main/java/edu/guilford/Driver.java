package edu.guilford;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Driver extends Application {

    private static Scene scene1;

    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        root.getChildren().add(new StartMenuPane());
        scene1 = new Scene(root, 1250, 650);
        stage.setScene(scene1);
        stage.show();
        
        // make the stage not resizable
        stage.setResizable(false);

        // set the title of the stage to Stop Right There
        stage.setTitle("Stop Right There");

        // set the icon of the stage to vinylDisc.png
        stage.getIcons().add(new Image("vinylDisc.png"));
    }

    public static void main(String[] args) {
        launch();
    }


}