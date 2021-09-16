package duke.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.Duke;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends Stage {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/TakeNRG.png"));
    private final Image dukeImage =
            new Image(this.getClass().getResourceAsStream("/images/GivePLZ.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.getInitMessage(), dukeImage, duke.hasException())
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        String response = duke.getResponse(input);
        // If a terminating command has been executed (e.g. ByeCommand)
        if (duke.isTerminated()) {
            Platform.exit();
            System.exit(0);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage, duke.hasException())
        );
        userInput.clear();
    }
}
