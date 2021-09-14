package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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

    private Duke duke;

    private final Image USER_IMAGE =
            new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image DUKE_IMAGE =
            new Image(this.getClass().getResourceAsStream("/images/Barry.jpg"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void startDuke(Duke d) {
        duke = d;
        printOutput(duke.getGui().getWelcomeMessage());
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
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getDukeDialog(response, DUKE_IMAGE)
        );
        userInput.clear();
    }

    /**
     * Print an output string to chatbox.
     * @param output the output to print.
     */
    @FXML
    public void printOutput(String output) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(output, DUKE_IMAGE)
        );
    }

    /**
     * Print an input string to chatbox.
     * @param input the input to print.
     */
    public void printInput(String input) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE)
        );
    }
}