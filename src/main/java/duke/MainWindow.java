package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for duke.MainWindow. Provides the layout for the other controls.
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

    /**
     * Initialises a mainWindow and a welcome message.
     */
    @FXML
    public void initialize() {
        String toWelcome = ("Hello I'm your friendly task-planning chatbot duke.Duke!\n"
                + "To enter a deadline, please type in this format:\n'deadline(or d) {title of item} "
                + "/by d/mm/yyyy hh:mm'\n"
                + "To enter an event, please type in this format: \n'event(or e) {title of item} /on d/mm/yyyy hh:mm'\n"
                + "To enter a todo, please type in this format: \n'todo(or t) {title of item}'\n"
                + "type help(or h) to know more commands available in this bot!");
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(toWelcome, dukeImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
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
    }
}
