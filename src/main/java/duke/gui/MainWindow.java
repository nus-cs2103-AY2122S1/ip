package duke.gui;

import java.time.LocalDate;
import java.util.Objects;

import duke.Duke;
import duke.TaskList;
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

    private Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/user.png")));
    private Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/duck.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Welcome to Duke!\n"
                        + "Please enter the tasks (todo/event/deadline) to be added to the list.\n"
                        + "(Enter 'list' to view the list, or 'bye' to exit.)", dukeImage)
        );
    }

    private void showReminder() {
        TaskList reminderList = duke.getTaskList().getTasksBefore(LocalDate.now().plusDays(7));
        if (!reminderList.isEmpty()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(duke.getUi().showReminderTasks(reminderList), dukeImage)
            );
        }
    }

    /**
     * Displays the start messages after launching Duke.
     */
    public void showStartText() {
        showWelcome();
        showReminder();
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
