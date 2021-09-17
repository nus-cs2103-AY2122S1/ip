package gnosis.ui;

import java.io.File;
import java.util.List;

import gnosis.controller.GnosisController;
import gnosis.model.Command;
import gnosis.model.Place;
import gnosis.model.Task;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * The GnosisUI handles the user interaction with user.
 * It focuses on listening for input and displaying output.
 *
 * @author Pawandeep Singh
 * */
public class GnosisUI extends AnchorPane {
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
    private ComboBox<Command> commandComboBox;


    @FXML
    private MenuItem closeMenu;


    /**
     * Initialises Gnosis UI before displaying to user.
     * */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        commandComboBox.getItems().addAll(Command.values());
        commandComboBox.setPromptText("Select Command");
    }

    /**
     * Handle command ComboBox when user selects it.
     * */
    @FXML
    public void commandComboBoxHandler() {
        Command command = commandComboBox.getValue();
        switch (command) {
        case BYE:
        case PLACE:
        case LIST:
            userInput.setEditable(false);
            userInput.setVisible(false);
            break;
        default:
            userInput.setVisible(true);
            userInput.setEditable(true);
        }
    }

    /**
     * Handles user input.
     * */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command command = commandComboBox.getValue();
        if (command == null) {
            return;
        }
        dialogContainer.getChildren().add(DialogBox.getUserDialog(command + " " + input));
        this.parseInput(input);
        userInput.clear();
    }

    /**
     * Sets a listener when user exports file.
     * */
    @FXML
    public void setExportCsvListener(ActionEvent event) {
        //allow user to export task to csv
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        MenuItem exportItem = (MenuItem) event.getTarget();
        File file = fileChooser.showSaveDialog(exportItem.getParentPopup().getOwnerWindow());

        //check for
        boolean isExportSuccess = gnosisController.export(exportItem.getText(), file);
        if (!isExportSuccess) {
            displayMessage("File not exported");
        } else {
            displayMessage("File Exported");
        }

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
        try {
            this.gnosisController.executeUserCommand(commandComboBox.getValue(), input);
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
     * @param isTaskDataAvailable Whether task file system is available or not.
     * @param isPlaceDataAvailable Whether place file system is available or not.
     */
    public void displayGreetMessage(boolean isTaskDataAvailable, boolean isPlaceDataAvailable) {

        String taskDataAvailableMessage =
                isTaskDataAvailable
                        ? GnosisConstants.DATA_TASK_FILE_FOUND_MESSAGE
                        : GnosisConstants.DATA_TASK_FILE_NOT_FOUND_MESSAGE;

        String placeDataAvailableMessage =
                isPlaceDataAvailable
                        ? GnosisConstants.DATA_PLACE_FILE_FOUND_MESSAGE
                        : GnosisConstants.DATA_PLACE_FILE_NOT_FOUND_MESSAGE;

        displayMessage(GnosisConstants.GREET_MESSAGE, taskDataAvailableMessage, placeDataAvailableMessage);
    }

    /**
     * Displays found tasks based on keyword.
     *
     * @param foundTasks filtered tasks that have matching keyword.
     * @param keyword keyword used to filter tasks.
     */
    public void displayFoundTasksMessage(List<Task> foundTasks, String keyword) {
        displayMessage(GnosisConstants.LISTING_MATCH_KEYWORD_MESSAGE + keyword);
        String message = "";

        for (Task task: foundTasks) {
            message = message.concat(task.toString() + "\n");
        }
        displayMessage(message);
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
            String message = "";
            for (int i = 0; i < len; i++) {
                message = message.concat((i + 1) + ". " + tasks.get(i) + "\n");
            }

            displayMessage(message);
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
     * Update Place view message from specified place command.
     *
     * @param place - place added.
     * @param numOfPlaces - Number of places visited.
     */
    public void updatePlaceManagementViewMessage(Place place, int numOfPlaces) {
        displayMessage(place.toString(), "Total places visited: " + numOfPlaces);
    }

    /**
     * Displays all places user have visited.
     *
     * @param places - place added.
     */
    public void displayAllPlaces(List<Place> places) {
        int len = places.size();
        if (len == 0) {
            displayMessage("No places you have visited.");
        } else {
            displayMessage("Listing all places visited:");
            String message = "";
            for (int i = 0; i < len; i++) {
                message = message.concat((i + 1) + ". " + places.get(i) + "\n");
            }
            displayMessage(message);

        }
    }

    /**
     * Displays messages in sequence.
     *
     * @param messages Messages to display to user.
     */
    public void displayMessage(String... messages) {

        String message = "";
        for (String msg: messages) {
            message = message.concat(msg + "\n");
        }

        dialogContainer.getChildren().add(DialogBox.getGnosisDialog(message));
    }

    /**
     * Displays bye message to user.
     *
     */
    public void displayByeMessage() {
        displayMessage(GnosisConstants.BYE_MESSAGE);
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    public void setCloseMenuListener() {
        Platform.exit();
    }
}
