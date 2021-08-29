package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * Main skeleton adapted from JavaFX tutorial 4: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class Main extends Application {

    private Duke duke = new Duke("taskList.txt");

    /**
     * Launches the GUI.
     *
     * @param stage Stage to hold the GUI scene.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            ScrollPane dialogPane = (ScrollPane) ap.getChildren().get(2);
            VBox dialogContainer = (VBox) dialogPane.getContent().lookup("#dialogContainer");
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog("Hello! I'm Duke!\nWhat can I do for you?",
                            new Image(this.getClass().getResourceAsStream("/images/dog.jpg")))
            );

            ScrollPane feedbackPane = (ScrollPane) ap.getChildren().get(3);
            Label feedback = (Label) feedbackPane.getContent().lookup("#feedback");
            feedback.setText(duke.getTasks());

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the GUI.
     */
    public static void exit() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }
}

