package me.yukun99.ip.ui.elements;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import me.yukun99.ip.commands.Command;
import me.yukun99.ip.core.Parser;
import me.yukun99.ip.core.Storage;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.exceptions.HelpBotInvalidCommandException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.exceptions.HelpBotIoException;
import me.yukun99.ip.ui.Message;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class Window extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField input;
    private Parser parser;
    private Storage storage;

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
     * @throws HelpBotIoException If sent messages or tasks could not be saved.
     */
    public void setup(Parser parser, Storage storage, String name) throws HelpBotIoException {
        this.parser = parser;
        this.storage = storage;
        dialogContainer.getChildren().add(DialogBox.getBotDialog(Message.getEnableMessage(name)));
        storage.saveMessage(Message.getEnableMessage(name));
    }

    /**
     * Creates 2 dialogue boxes.
     * First message echoes the user's commands.
     * Second message is the HelpBot's reply.
     */
    @FXML
    public void parseMessage() {
        displayMessage(input.getText(), false);
        try {
            storage.saveMessage(input.getText());
            Command command = parser.parseCommand(input.getText());
            String response = command.getResponse();
            displayMessage(response, true);
            storage.saveMessage(response);
        } catch (HelpBotInvalidCommandException | HelpBotIllegalArgumentException | HelpBotInvalidTaskTypeException
                | HelpBotDateTimeFormatException | HelpBotIoException e) {
            dialogContainer.getChildren().add(DialogBox.getBotDialog(Message.getErrorMessage(e)));
        } finally {
            input.clear();
        }
    }

    /**
     * Displays a message on the HelpBot GUI.
     *
     * @param message Message to be displayed on the HelpBot GUI.
     * @param isBot Whether the message is sent by the bot.
     */
    public void displayMessage(String message, boolean isBot) {
        if (isBot) {
            dialogContainer.getChildren().add(DialogBox.getBotDialog(message));
        } else {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(message));
        }
    }
}
