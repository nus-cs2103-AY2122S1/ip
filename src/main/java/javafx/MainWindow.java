package javafx;

import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    private Ui dukeUi;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Ui dukeUi) {
        this.dukeUi = dukeUi;
    }

    /**
     * Displays a start message to the user.
     */
    public void displayStartMessage() {
        this.dialogContainer.getChildren().addAll(
                DialogBox.getStartDialog()
        );
    }

    /**
     * Takes a user input and evaluates it accordingly. It is triggered
     * when the user press the send button on the GUI or presses enter
     * on his keyboard.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(getResponse(input))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return this.dukeUi.readUserInput(input.strip());
    }
}
