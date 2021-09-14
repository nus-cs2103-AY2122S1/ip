package seedu.duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.duke.task.TaskList;

/**
 * Controller for seedu.duke.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends Stage {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button reminderButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/boh.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/kaonashi.gif"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.hvalueProperty().bind(anchorPane.widthProperty());
        dialogContainer.setFillWidth(true);
    }

    public void setDuke(Duke d) {
        duke = d;
        duke.init();
        handleStart();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
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

        if (duke.getExit()) {
            Platform.exit();
            System.exit(0);
        }
    }

    @FXML
    private void handleReminder() {
        String response = duke.getReminder();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(response);
        alert.show();
    }

    private void handleStart() {
        String greeting = duke.getGreeting();
        TaskList taskList = duke.getTaskList();
        String currentList = taskList.status() + "\n" + taskList;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, dukeImage),
                DialogBox.getDukeDialog(currentList, dukeImage)
        );
        handleReminder();
    }

}
