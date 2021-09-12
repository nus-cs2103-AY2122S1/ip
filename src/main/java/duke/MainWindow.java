package main.java.duke;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.java.duke.commands.Command;
import main.java.duke.commands.ContactCommand;
import main.java.duke.commands.ExitCommand;

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
    @FXML private javafx.scene.control.Button closeButton;


    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html
    //with minor modifications
    @FXML
    private void handleUserInput() throws IOException, DukeException {
        String input = userInput.getText();
        try {
            String response = getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (DukeException e) {
            showError(e.getMessage(), input);
        }
        userInput.clear();
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html
    protected String getResponse(String input) throws DukeException, IOException {
        String response = "";
        try {
            String fullCommand = readCommand();
            Command c = Parser.parse(fullCommand);
            if (c instanceof ExitCommand) {
                System.exit(0);
            } else if (c instanceof ContactCommand) {
                response = c.execute(duke.contacts, duke.gui, duke.contactsStorage);
            } else {
                response = c.execute(duke.tasks, duke.gui, duke.tasksStorage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * Shows welcome message and creates scanner for user input.
     */
    public void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello from Neko!\nWhat can I do for you?\n", dukeImage)
        );
    }

    /**
     * Displays the error message.
     * @param error the error message
     */
    public void showError(String error, String input) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(error, dukeImage)
        );
    }

    /**
     * Displays loading error message.
     */
    public void showLoadingError() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Oops, error in loading document...", dukeImage)
        );
    }

    /**
     * Reads user input from scanner.
     * @return the user input as string
     */
    public String readCommand() {
        return userInput.getText();
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Platform.exit();
    }
}
