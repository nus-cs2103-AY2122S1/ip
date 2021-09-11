package duke.ui;

import java.util.List;
import java.util.Map;

import duke.ChadChatbot;
import duke.command.Command;
import duke.task.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
         * Adds a text representation of a command.
         *
         * @param command The command.
         * @return This MessageBuilder instance.
         */
        public MessageBuilder addCommand(Command command) {
            messageSb.append(messageFormatter.formatCommand(command)).append("\n");
            return this;
        }

        /**
         * Displays the message in with formatting.
         */
        public void printFormatted() {
            String message = messageSb.toString().stripTrailing();
            displayMessage(message);
        }
    }

    private static final String GREETING_MESSAGE = "Hello there! My name is Chad!\n"
            + "What can I help you with?";
    private static final String EXIT_MESSAGE = "Goodbye. Hope we see each other again soon.";
    private static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error has occurred.";
    private static final String INVALID_COMMAND_ERROR_TEMPLATE = "This command is invalid.\n%s\nPlease try again.";

    @FXML
    private GridPane gridPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage;
    private Image botNormalImage;
    private Image botErrorImage;
    private boolean isAtBottom;
    private boolean wasAtBottom;
    private boolean hasNewDialog;

    private ChadChatbot chadChatbot;
    private MessageFormatter messageFormatter;

    /**
     * Creates a Ui instance.
     */
    public Ui() {
        messageFormatter = MessageFormatter.getInstance();
        userImage = new Image(this.getClass().getResourceAsStream("/images/yeschad_toleft.png"));
        botNormalImage = new Image(this.getClass().getResourceAsStream("/images/gigachad_toright.png"));
        botErrorImage = new Image(this.getClass().getResourceAsStream("/images/gigachad_error_toright.png"));
        isAtBottom = true;
        wasAtBottom = true;
        hasNewDialog = false;
    }

    /**
     * Displays the greeting.
     */
    public void printGreeting() {
        displayMessage(GREETING_MESSAGE);
    }

    /**
     * Displays the generic error message for unexpected errors.
     */
    public void printUnexpectedErrorMessage() {
        displayErrorMessage(UNEXPECTED_ERROR_MESSAGE);
    }

    /**
     * Displays the error message for when an invalid command occurs.
     *
     * @param message The error message.
     */
    public void printInvalidCommandErrorMessage(String message) {
        displayErrorMessage(String.format(INVALID_COMMAND_ERROR_TEMPLATE, message));
    }

    /**
     * Displays the exit message.
     */
    public void printExitMessage() {
        displayMessage(EXIT_MESSAGE);
    }

    /**
     * Initiates the message-building process.
     *
     * @return The MessageBuilder instance used in this message-building process.
     */
    public MessageBuilder startMessage() {
        return new MessageBuilder();
    }

    public void setDukeChatbot(ChadChatbot chadChatbot) {
        this.chadChatbot = chadChatbot;
    }

    @FXML
    public void initialize() {
        initialiseScrollPane();
        initialiseDialogContainer();
    }

    private void initialiseScrollPane() {
        makeScrollPaneCheckIfItWasScrolledToBottom();
    }

    private void initialiseDialogContainer() {
        makeDialogContainerTellScrollPaneToScrollToBottomAppropriately();
        makeDialogContainerCheckIfNewDialogsAdded();
    }

    private void makeScrollPaneCheckIfItWasScrolledToBottom() {
        scrollPane.vvalueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                wasAtBottom = isAtBottom;
                double vValue = newValue.doubleValue();
                double maxVValue = scrollPane.getVmax();
                boolean isNotAtBottom = vValue < maxVValue;
                isAtBottom = !isNotAtBottom;
            }
        });
    }

    private void makeDialogContainerTellScrollPaneToScrollToBottomAppropriately() {
        dialogContainer.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (wasAtBottom) {
                    scrollToBottom();
                } else if (hasNewDialog) {
                    scrollToBottom();
                    hasNewDialog = false;
                }
            }
        });
    }

    private void makeDialogContainerCheckIfNewDialogsAdded() {
        dialogContainer.getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> c) {
                while (c.next() && c.wasAdded()) {
                    hasNewDialog = true;
                }
            }
        });
    }

    private void scrollToBottom() {
        scrollPane.setVvalue(scrollPane.getVmax());
    }

    /**
     * Processes the user's input.
     * Creates one dialog box echoing user input and then appends them to the dialog container.
     * Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html written by Jeffry Lum.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DialogBox userDialog = DialogBox.getCommandDialog(input, userImage);
        dialogContainer.getChildren()
                .add(userDialog);
        userInput.clear();
        chadChatbot.readInput(input);
    }

    private String getListLengthMessage(int listLength) {
        // Check whether singular or plural should be printed.
        if (listLength != 1) {
            return String.format("Now you have %d tasks in the list.", listLength);
        } else {
            return "Now you have 1 task in the list.";
        }
    }

    private void displayMessage(String message) {
        String formattedMessage = messageFormatter.getFormattedMessage(message);
        DialogBox dukeDialog = DialogBox.getNormalResponseDialog(formattedMessage, botNormalImage);
        dialogContainer.getChildren()
                .add(dukeDialog);
    }

    private void displayErrorMessage(String message) {
        String formattedMessage = messageFormatter.getFormattedMessage(message);
        DialogBox dukeDialog = DialogBox.getErrorResponseDialog(formattedMessage, botErrorImage);
        dialogContainer.getChildren()
                .add(dukeDialog);
    }
}
