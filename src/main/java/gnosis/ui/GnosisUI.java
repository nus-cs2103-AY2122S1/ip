package gnosis.ui;

import java.util.List;
import java.util.Scanner;

import gnosis.controller.GnosisController;
import gnosis.model.Task;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The GnosisUI handles the user interaction with user.
 * It focuses on listening for input and displaying output.
 *
 * @author Pawandeep Singh
 * */
public class GnosisUI extends AnchorPane {

    //TODO: Variable Comments

    private GnosisController gnosisController;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField userInput;

    @FXML
    private VBox dialogContainer;

    @FXML
    private Button sendButton;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Gnosis's reply and then appends them to
     * the dialog container. Clears user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input));
        this.parseInput(input);

        userInput.clear();
    }

    /**
     * Sets up connection with Gnosis and loads greeting message.
     *
     * @param gnosisController Gnosis controller that handles main logic.
     */
    public void setUpGnosis(GnosisController gnosisController) {
        this.gnosisController = gnosisController;
        gnosisController.loadGreetingMessage();
    }

    /**
     * gnosisView starts to listen and wait for user input.
     *
     * @param input User input to parse.
     */
    public void parseInput(String input) {
        Scanner sc = new Scanner(input);

        try {
            String command = sc.next();
            String action = "";
            if (sc.hasNext()) {
                action = sc.nextLine();
            }

            this.gnosisController.executeCommand(command, action);

        } catch (GnosisException ge) {
            displayMessage(ge.toString());
        } catch (NumberFormatException nfe) {
            displayMessage(GnosisConstants.DONE_COMMAND_NUM_INPUT_EXCEPT_MESSAGE);
        }
    }

    /**
     * Displays greeting message to user with message of
     * whether file system is available or not.
     *
     * @param isDataAvailable Whether file system is available or not.
     */
    public void displayGreetMessage(boolean isDataAvailable) {
        displayMessage(GnosisConstants.GREET_MESSAGE,
                        isDataAvailable
                        ? GnosisConstants.DATA_TASK_FILE_FOUND_MESSAGE
                        : GnosisConstants.DATA_TASK_FILE_NOT_FOUND_MESSAGE);
    }

    /**
     * Displays found tasks based on keyword.
     *
     * @param foundTasks filtered tasks that have matching keyword.
     * @param keyword keyword used to filter tasks.
     */
    public void displayFoundTasksMessage(List<Task> foundTasks, String keyword) {
        displayMessage(GnosisConstants.LISTING_MATCH_KEYWORD_MESSAGE + keyword);
        for (Task task: foundTasks) {
            displayMessage(task.toString());
        }

    }

    /**
     * Displays Task view message from specified task command.
     *
     * @param action - Command user has specified
     * @param task - specified task as a result of user command
     * @param numOfTasks - number of task
     */
    public void updateTaskManagementViewMessage(String action, Task task, int numOfTasks) {
        displayMessage("Task " + action + ":", task.toString(), "Total tasks in the list: " + numOfTasks);
    }

    /**
     * Displays all tasks to user.
     *
     * @param tasks - list of all tasks
     */
    public void displayAllTasksMessage(List<Task> tasks) {
        int len = tasks.size();
        if (len == 0) {
            displayMessage("There is no task in the list.");
        } else {
            displayMessage("Listing all tasks in list:");
            for (int i = 0; i < len; i++) {
                displayMessage((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Displays task marked status to user.
     *
     * @param task - specified task to display for mark status
     * @param taskIndex - task number from task list
     */
    public void displayMarkedTaskMessage(Task task, int taskIndex) {
        displayMessage("Task " + (taskIndex) + " marked as done:", "\t" + task);
    }

    /**
     * Displays bye message to user.
     *
     */
    public void displayByeMessage() {
        displayMessage(GnosisConstants.BYE_MESSAGE);
        Platform.exit();
    }

    /**
     * Displays messages in sequence
     *
     * @param messages Messages to display to user.
     */
    public void displayMessage(String... messages) {
        for (String msg: messages) {
            dialogContainer.getChildren().add(DialogBox.getGnosisDialog(msg));
        }
    }
}
