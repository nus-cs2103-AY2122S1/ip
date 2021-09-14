package duke;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import duke.util.Ui;

import duke.frontend.DialogBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    
    @FXML
    /**
     * Initializes main window.
     */
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke for the program.
     *
     * @param d Duke instance.
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.greet(), dukeImage));
    }
    
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String output = duke.getBotOutput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(output, dukeImage)
        );
        userInput.clear();
        if (duke.isExit()) {
            exitGui();
        }
    }

    /**
     * Exits GUI with a 2 second delay.
     */
    private void exitGui() {
        PauseTransition endingDelay = new PauseTransition((Duration.seconds(2)));
        endingDelay.setOnFinished(event -> System.exit(0));
        endingDelay.play();
    }
}