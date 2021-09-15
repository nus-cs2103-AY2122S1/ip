package jarvis;

import java.io.IOException;

import javafx.animation.PauseTransition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.util.Duration;

/**
 * A GUI for Jarvis using FXML.
 */
public class Main extends Application {
    private Jarvis jarvis = new Jarvis("data/jarvis.txt", "data/notes.txt"); // Instantiating Jarvis

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
            stage.setTitle("Jarvis");
            fxmlLoader.<MainWindow>getController().setJarvis(jarvis);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the chat bot window when the user is done using the application
     */
    public static void end() {
        // The chat bot window closes 1 second after the user keys in 'bye' so that they are able to see
        // Jarvis' response
        PauseTransition durationToWaitBeforeClosing = new PauseTransition(Duration.seconds(1));
        durationToWaitBeforeClosing.setOnFinished(event -> Main.currStage.close()); // Chat bot window closes
        durationToWaitBeforeClosing.play();
    }
}
