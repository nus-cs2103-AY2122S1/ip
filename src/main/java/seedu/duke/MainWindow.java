package seedu.duke;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

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
    private Storage storage;
    private HashMap<LocalDate, ArrayList<Task>> dateTasks;
    private TaskList taskList;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.hvalueProperty().bind(anchorPane.widthProperty());
        dialogContainer.setFillWidth(true);
    }

    public void setDuke(Duke d) {
        duke = d;
        storage = duke.getStorage();
        dateTasks = duke.getDateTasks();
        taskList = duke.getTaskList();
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
        }
    }

    @FXML
    private void handleReminderClick() {
        String response = duke.getReminder();
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText(response);
        alert.show();
    }

    private void handleStart() {
        String greeting = duke.getGreeting();
        String currentList = taskList.status() + "\n" + taskList.toString();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, dukeImage),
                DialogBox.getDukeDialog(currentList, dukeImage)
        );
    }

}
