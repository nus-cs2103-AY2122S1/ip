package duke.command;

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
    private static final String DOTTED_LINES = "-".repeat(80);
    private static final String LOADING_ERROR = DOTTED_LINES + "\nFile cannot be created\n" + DOTTED_LINES;
    private static final String SAVING_ERROR = DOTTED_LINES + "\nFile cannot be saved\n" + DOTTED_LINES;
    private static final String WELCOME_MESSAGE = DOTTED_LINES +
            "\nHello I'm LOTTERY-A\n" +
            "Also known as the List Of Tasks That Eventually Require Your Attention\n" +
            "What can I do for you?\n" +
            DOTTED_LINES;
    private static final String BYE_MESSAGE = DOTTED_LINES +
            "\nBye. Don't forget, these tasks will still require your attention when you return!\n" +
            DOTTED_LINES;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private MyParser parser = new MyParser();

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaLottery.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
        input.trim();
        int commandIndex = input.indexOf(" ");
        String command;
        String description;
        if (commandIndex == -1) {
            command = input;
            description = "";
        } else {
            command = input.substring(0, commandIndex);
            description = input.substring(commandIndex);
        }

        try {
            parser.parse(command, description, duke);
        } catch (DukeException e) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(e.getMessage(), dukeImage));
        }

        userInput.clear();
    }



    /** Shows welcome message. */
    public void showWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(WELCOME_MESSAGE, dukeImage));
    }

    /** Shows loading error. */
    public void showLoadingError() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(LOADING_ERROR, dukeImage));
    }

    /** Shows saving error. */
    public void showSavingError() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(SAVING_ERROR, dukeImage));
    }

    /** Shows exit message. */
    public void showByeMessage() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(BYE_MESSAGE, dukeImage));
    }

    /** Shows that task has been added. */
    public void showAddTaskMessage(String taskDesc, int size) {
        String addTaskMessage = DOTTED_LINES +
                "\nGot it. I've added this task:\n" +
                taskDesc +
                "\nNow you have " + size + " tasks in the list\n" +
                DOTTED_LINES;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(addTaskMessage, dukeImage));
    }

    /** Shows that task has been deleted. */
    public void showDeleteTaskMessage(String taskDesc, int size) {
        String deleteTaskMessage = DOTTED_LINES +
                "\nNoted. I've removed this task:\n" +
                taskDesc +
                "\nNow you have " + size + " tasks in the list\n" +
                DOTTED_LINES;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(deleteTaskMessage, dukeImage));
    }

    /** Shows that task has been marked as done. */
    public void showMarkAsDoneMessage(String taskDesc) {
        String markAsDoneMessage = DOTTED_LINES +
                "\nNice! I've marked this task as done:\n" +
                taskDesc +
                DOTTED_LINES;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(markAsDoneMessage, dukeImage));
    }

    public void showListOfTasks(String taskList) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(taskList, dukeImage));
    }

}