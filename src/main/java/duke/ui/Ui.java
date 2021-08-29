package duke.ui;

import java.util.List;
import java.util.Map;

import duke.DukeChatbot;
import duke.task.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Helper class to handle UI functionality.
 *
 * @author Jay Aljelo Saez Ting
 */
public class Ui extends AnchorPane {

    /**
     * Builder class to help with creating messages to be printed in the UI.
     *
     * @author Jay Aljelo Saez Ting
     */
    public class MessageBuilder {

        private StringBuilder messageSb;

        private MessageBuilder() {
            this.messageSb = new StringBuilder();
        }

        /**
         * Adds a line of text.
         *
         * @param line The line of text.
         * @return This MessageBuilder instance.
         */
        public MessageBuilder addLine(String line) {
            messageSb.append(line.stripTrailing()).append("\n");
            return this;
        }

        /**
         * Adds a text representation of a task.
         *
         * @param task The task to be represented.
         * @return This MessageBuilder instance.
         */
        public MessageBuilder addTask(Task task) {
            messageSb.append(messageFormatter.formatTask(task)).append("\n");
            return this;
        }

        /**
         * Adds a message indicating the tasks list length.
         *
         * @param length The length of the tasks list.
         * @return This MessageBuilder instance.
         */
        public MessageBuilder addTasksListLength(int length) {
            messageSb.append(getListLengthMessage(length)).append("\n");
            return this;
        }

        /**
         * Adds a text representation of the tasks list.
         *
         * @param tasks The tasks list.
         * @return This MessageBuilder instance.
         */
        public MessageBuilder addTasksList(List<Task> tasks) {
            messageSb.append(messageFormatter.formatTasksList(tasks)).append("\n");
            return this;
        }

        /**
         * Adds a text representation of a "Find Tasks" command's results.
         *
         * @param queryResults The results of the "Find Tasks" command.
         * @return This MessageBuilder instance.
         */
        public MessageBuilder addFindTasksResultsList(List<Map.Entry<Integer, Task>> queryResults) {
            messageSb.append(messageFormatter.formatFindTasksResultsList(queryResults)).append("\n");
            return this;
        }

        /**
         * Displays the message in with formatting.
         */
        public void printFormatted() {
            String message = messageSb.toString().stripTrailing();
            printFormattedMessage(message);
        }
    }

    private static final String GREETING_MESSAGE = "Hello! I'm Duke!\n"
            + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error has occurred.";
    private static final String INVALID_COMMAND_ERROR_TEMPLATE = "This command is invalid.\n%s\nPlease try again.";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private DukeChatbot dukeChatbot;
    private MessageFormatter messageFormatter;

    /**
     * Creates a Ui instance.
     */
    public Ui() {
        this.messageFormatter = MessageFormatter.getInstance();
    }

    /**
     * Displays the greeting.
     */
    public void printGreeting() {
        printFormattedMessage(GREETING_MESSAGE);
    }

    /**
     * Displays the generic error message for unexpected errors.
     */
    public void printUnexpectedErrorMessage() {
        printFormattedMessage(UNEXPECTED_ERROR_MESSAGE);
    }

    /**
     * Displays the error message for when an invalid command occurs.
     *
     * @param message The error message.
     */
    public void printInvalidCommandErrorMessage(String message) {
        printFormattedMessage(String.format(INVALID_COMMAND_ERROR_TEMPLATE, message));
    }

    /**
     * Displays the exit message.
     */
    public void printExitMessage() {
        printFormattedMessage(EXIT_MESSAGE);
    }

    /**
     * Initiates the message-building process.
     *
     * @return The MessageBuilder instance used in this message-building process.
     */
    public MessageBuilder startMessage() {
        return new MessageBuilder();
    }

    public void setDukeChatbot(DukeChatbot dukeChatbot) {
        this.dukeChatbot = dukeChatbot;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Processes the user's input.
     * Creates one dialog box echoing user input and then appends them to the dialog container.
     * Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html written by Jeffry Lum.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();
        dukeChatbot.readInput(input);
    }

    private String getListLengthMessage(int listLength) {
        // Check whether singular or plural should be printed.
        if (listLength != 1) {
            return String.format("Now you have %d tasks in the list.", listLength);
        } else {
            return "Now you have 1 task in the list.";
        }
    }

    private void printFormattedMessage(String message) {
        String formattedMessage = messageFormatter.getFormattedMessage(message);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(formattedMessage, dukeImage));
    }
}
