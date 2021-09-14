package duke;

import java.io.IOException;

import javafx.animation.PauseTransition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.util.Duration;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke jarvis = new Duke("data/jarvis.txt", "data/notes.txt"); // Instantiating Jarvis

    private static Stage currStage;
    /**
     * Sets up the GUI for Jarvis
     *
     * @param stage The space to which additional scenes and panes are added for the GUI
     */
    @Override
    public void start(Stage stage) {
        try {
            Main.currStage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(jarvis);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void end() {
        PauseTransition durationToWaitBeforeClosing = new PauseTransition(Duration.seconds(1));
        durationToWaitBeforeClosing.setOnFinished(event -> Main.currStage.close());
        durationToWaitBeforeClosing.play();
    }
}
