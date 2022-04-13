package duke.ui;

import duke.Duke;
import duke.exception.DukeException;
import javafx.application.Platform;
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
            new Image(this.getClass().getResourceAsStream("/images/Barry.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Start the duke chatbot by printing the welcome message along with any start up errors.
     * @param d the duke to be started.
     */
    public void startDuke(Duke d) {
        duke = d;
        printOutput(Gui.getWelcomeMessage());
        try {
            duke.start();
        } catch (DukeException e) {
            printOutput(e.getMessage());
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        printInput(input);
        printOutput(response);
        userInput.clear();
        if (response.equals(Gui.getExitMessage())) {
            Platform.exit();
        }
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
    @FXML
    public void printInput(String input) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE)
        );
    }
}