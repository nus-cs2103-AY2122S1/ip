package duke.gui;

import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * Adapted from:
 * Lum, Jeffry (2021) JavaFX tutorial. https://se-education.org/guides/tutorials/javaFx.html
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/otaku.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/mafu.png"));

    /**
     * Initializes the height of the scroll pane to adjust to the height of dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets duke instance of MainWindow.
     *
     * @param d Duke instance
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Displays greeting on GUI.
     */
    public void displayGreeting() {
        printMessage(duke.getUi().showGreet());
    }

    /**
     * Prints message onto the GUI.
     *
     * @param string String to print
     */
    public void printMessage(String string) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(string, dukeImage));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        /*
         * Adapted from:
         * James_D (2014) StackOverflow: How to close a stage after a certain amount of time JavaFX
         * https://stackoverflow.com/a/27334614
         */
        if (duke.isQuit()) {
            PauseTransition pauseThenExit = new PauseTransition(Duration.seconds(0.5));
            pauseThenExit.setOnFinished(event -> Platform.exit());
            pauseThenExit.play();

        }
    }
}
