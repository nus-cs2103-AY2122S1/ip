package me.yukun99.ip.ui.elements;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import me.yukun99.ip.ui.Message;
import me.yukun99.ip.ui.Ui;

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
    @FXML
    private Button sendButton;
    private Parser parser;
    private Ui ui;
    private Storage storage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setup(Parser parser, Ui ui, Storage storage, String name) {
        this.parser = parser;
        this.ui = ui;
        this.storage = storage;
        dialogContainer.getChildren().add(DialogBox.getBotDialog(Message.getEnableMessage(name)));
        try {
            storage.saveMessage(Message.getEnableMessage(name));
        } catch (IOException ignored) {
            //ignored
        }
    }

    /**
     * Creates 2 dialogue boxes.
     * First message echoes the user's commands.
     * Second message is the HelpBot's reply.
     */
    @FXML
    public void parseMessage() {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input.getText()));
        try {
            storage.saveMessage(input.getText());
        } catch (IOException ignored) {
            //ignored
        }
        try {
            Command command = parser.parseCommand(input.getText());
            dialogContainer.getChildren().add(DialogBox.getBotDialog(command.getResponse()));
            try {
                storage.saveMessage(command.getResponse());
            } catch (IOException ignored) {
                // ignored
            }
        } catch (HelpBotInvalidCommandException | HelpBotIllegalArgumentException
                | HelpBotInvalidTaskTypeException | HelpBotDateTimeFormatException e) {
            dialogContainer.getChildren().add(DialogBox.getBotDialog(Message.getErrorMessage(e)));
        } finally {
            input.clear();
        }
    }
}
