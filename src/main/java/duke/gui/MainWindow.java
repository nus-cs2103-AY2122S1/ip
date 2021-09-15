package duke.gui;

import duke.command.Command;
import duke.main.Duke;
import duke.main.DukeException;
import duke.main.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        this.duke = d;
    }

    @FXML
    private void handleUserInput() {
        String userCommand = userInput.getText();
        Label input = new Label(userCommand);
        Label response;
        boolean error = false;
        try {
            Command command = Parser.parse(userCommand);
            String message = command.execute(duke.getTasks(), duke.getUi(), duke.getStorage());
            response = new Label(message);
        } catch (DukeException e) {
            response = new Label(duke.getUi().displayError(e));
            error = true;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(response, error)
        );
        userInput.clear();
    }

    /**
     * Sends the user a greeting message.
     */
    public void greet() {
        Label greeting = new Label(duke.getUi().greet());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, false)
        );
    }
}
