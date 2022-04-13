package dac.gui;

import java.io.IOException;
import java.util.Objects;

import dac.DaC;
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

    public static final String FILE_PATH = System.getProperty("user.home") + "/taskList.txt";
    public static final String GREETING = "Hello! Welcome to Dog-and-Cat!\n"
            + "I am a dog and you are a cat.\n"
            + "What can I do for you?";
    private final DaC daC = new DaC(FILE_PATH);

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
                    DialogBox.getDukeDialog(GREETING,
                            new Image(Objects
                                    .requireNonNull(this.getClass().getResourceAsStream("/images/dog.jpg"))))
            );

            ScrollPane feedbackPane = (ScrollPane) ap.getChildren().get(3);
            Label feedback = (Label) feedbackPane.getContent().lookup("#feedback");
            feedback.setText(daC.getTasks());

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(daC);

            stage.setTitle("Dog-and-Cat (DaC)");
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
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }
}

