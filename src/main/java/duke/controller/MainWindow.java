package duke.controller;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private String lineBreak = "------------------------------";

    private final String WELCOME_MESSAGE = "Hello! I'm Duke.\n"
            + "How can I help you today?\n"
            + lineBreak + "\n"
            + "You can create Tasks here.\n"
            + "To add a Todo, \n"
            + " simply key in `todo` followed by the name;\n"
            + "To add a Deadline, \n"
            + " simply key in `deadline` followed by its name, `/by` and the deadline;\n"
            + "To add an Event, \n"
            + " simply key in `event` followed by its name, `/at` and its time.\n"
            + "Please key in the date and time in the format of `26/08/2021 20:20`.\n"
            + lineBreak + "\n"
            + "To display the current list, \n"
            + " simply enter 'list'.\n"
            + "To delete a task, \n"
            + " simply enter 'delete' and space, followed by the task index.\n"
            + "To mark a task as done, \n"
            + " simple enter 'done' and space, followed by the task index.\n"
            + "To exit, \n"
            + " simply enter 'bye'.\n";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(WELCOME_MESSAGE, dukeImage)
        );
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
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
