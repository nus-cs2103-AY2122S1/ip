package me.yukun99.ip.ui.elements;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import me.yukun99.ip.commands.Command;
import me.yukun99.ip.core.Parser;
import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.exceptions.HelpBotInvalidCommandException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.exceptions.HelpBotIoException;
import me.yukun99.ip.ui.Message;

/**
 * Controller for main Window.
 */
public class Window extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField input;
    @FXML
    private Label taskAmount;
    @FXML
    private Button sendButton;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets up the HelpBot GUI window.
     *
     * @param parser Parser instance to parse HelpBot commands with.
     * @param storage Storage instance to save and load HelpBot tasks with.
     * @param name Name of the HelpBot to be used in the GUI.
     * @param taskList TaskList instance from the current HelpBot.
     * @throws HelpBotIoException If sent messages or tasks could not be saved.
     */
    public void setup(Parser parser, Storage storage, String name, TaskList taskList) throws HelpBotIoException {
        this.parser = parser;
        this.storage = storage;
        this.taskList = taskList;
        updateTaskAmount();
        displayMessage(Message.getEnableMessage(name), true, false);
        storage.saveMessage(Message.getEnableMessage(name));
    }

    /**
     * Creates 2 dialogue boxes.
     * First message echoes the user's commands.
     * Second message is the HelpBot's reply.
     */
    @FXML
    public void displayParsedMessage() {
        displayMessage(input.getText(), false, false);
        try {
            storage.saveMessage(input.getText());
            Command command = parser.parseCommand(input.getText());
            String response = command.getResponse();
            displayMessage(response, true, false);
            storage.saveMessage(response);
        } catch (HelpBotInvalidCommandException | HelpBotIllegalArgumentException | HelpBotInvalidTaskTypeException
                | HelpBotDateTimeFormatException | HelpBotIoException e) {
            displayMessage(Message.getErrorMessage(e), true, true);
            try {
                storage.saveMessage(Message.getErrorMessage(e));
            } catch (HelpBotIoException ignored) {
                //ignored as it's fine if we don't save this error.
            }
        } finally {
            input.clear();
            updateTaskAmount();
        }
    }

    private void updateTaskAmount() {
        taskAmount = taskList.updateTaskAmount(taskAmount);
    }

    /**
     * Displays a message on the HelpBot GUI.
     *
     * @param message Message to be displayed on the HelpBot GUI.
     * @param isBot Whether the message is sent by the bot.
     * @param isError Whether the message sent is an error message.
     */
    public void displayMessage(String message, boolean isBot, boolean isError) {
        if (isBot) {
            dialogContainer.getChildren().add(DialogBox.getBotDialog(message, isError));
        } else {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(message));
        }
    }
}
