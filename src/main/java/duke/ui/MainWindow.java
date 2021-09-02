package duke.ui;

import java.io.IOException;
import java.util.Objects;

import duke.Duke;
import duke.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
    @FXML
    private Button sendButton;

    private Image userImg = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/doge_circle.png")));
    private Image dukeImg = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/gigachad.jpg")));

    private Duke duke;
    private Ui ui; // For getting output messages
    // 0 -> 1 (with name) -> 2 (with file location) -> 3 (with task list size)
    private int sequence = 0; // Checks to see if the user inputs their name, file location, and task list size.
    private String filePath; // To temporarily store the file path to create duke.

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String dukeText;
        switch (sequence) {
        case 0: // start
            ui = new Ui(userInput.getText());
            sequence++;
            dukeText = 
                String.format("Hi, %s! That is a nice name. Now, where would you want to store your duke data files?",
                    userInput.getText());
            break;
        case 1: // get file location
            filePath = userInput.getText();
            sequence++; // We can't create duke here yet! We need the max size
            dukeText = "Ok, so what should the maximum size of the list be?";
            break;
        case 2:
            try {
                duke = new Duke(filePath, Integer.parseInt(userInput.getText()));
                dukeText = "Great! You are all set! How can I help you today?";
                sequence++;
            } catch (NumberFormatException e) {
                dukeText = "This is not a whole number. Please enter something sensible!";
            } catch (IOException e) {
                dukeText = e.getMessage();
                sequence--; // Go back one step
            }
            break;
        case 3:
            try {
                dukeText = getResponse(userInput.getText());
            } catch (DukeException e) {
                dukeText = e.getMessage();
            }
            break;
        default:
            dukeText = "Something went very wrong!";
        }

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userInput.getText(), userImg),
            DialogBox.getDukeDialog(dukeText, dukeImg)
        );
        userInput.clear();
        if (ui.willExit()) {
            try {
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getResponse(String input) {
        return ui.checkInput(input, duke);
    }
}
