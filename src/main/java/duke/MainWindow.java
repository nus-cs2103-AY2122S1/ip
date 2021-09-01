package main.java.duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.java.duke.commands.Command;

import java.io.IOException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException, DukeException {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    protected String getResponse(String input) throws DukeException, IOException {
        String response = "";
            try {
                String fullCommand = readCommand();
                Command c = Parser.parse(fullCommand);
                response = c.execute(duke.tasks, duke.gui, duke.storage);
            } catch (DukeException e) {
                response = showError(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }

        return response;
    }

    /**
     * Shows welcome message and creates scanner for user input.
     * @return the welcome message
     */
    public static String showWelcome() {
        return "Hello from Neko!\nWhat can I do for you?\n" ;
    }

    /**
     * Displays the error message.
     * @param error the error message
     * @return the error message
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Displays loading error message.
     */
    public String showLoadingError() {
        return "Oops! Error in loading the document...";
    }

    /**
     * Reads user input from scanner.
     * @return the user input as string
     */
    public String readCommand() {
        return userInput.getText();
    }
}