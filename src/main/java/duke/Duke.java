package duke;

import duke.data.TaskFileStorage;
import duke.exceptions.DukeException;
import duke.fulfillment.FulfillmentHandler;
import duke.io.ConsoleUserInputHandler;
import duke.io.ConsoleUserOutputHandler;
import duke.io.GuiUserInputHandler;
import duke.io.GuiUserOutputHandler;
import duke.io.UserInputHandler;
import duke.io.UserOutputHandler;
import duke.messages.Message;
import duke.tasks.TaskList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main class representing the Duke helper chatbot.
 *
 * @author kevin9foong
 */
public class Duke extends Application {
    private FulfillmentHandler fulfillmentHandler;

    /**
     * Constructs an instance of the Duke chat bot using CLI based input and output.
     */
    public Duke() {
        UserInputHandler userInputHandler = new ConsoleUserInputHandler();
        UserOutputHandler userOutputHandler = new ConsoleUserOutputHandler();
        try {
            TaskList taskList = new TaskList(new TaskFileStorage());
            this.fulfillmentHandler = new FulfillmentHandler(userInputHandler,
                    userOutputHandler, taskList);
        } catch (DukeException de) {
            userOutputHandler.writeMessage(new Message(de.getMessage()));
        }
    }

    /**
     * Constructs an instance of Duke chat bot with GUI elements as the user's input
     * and output method.
     *
     * @param userInputField     text field which user inputs command into.
     * @param userOutputTextArea text area to display chat bot response.
     */
    public Duke(TextField userInputField, TextArea userOutputTextArea) {
        UserInputHandler userInputHandler = new GuiUserInputHandler(userInputField);
        UserOutputHandler userOutputHandler = new GuiUserOutputHandler(userOutputTextArea);
        try {
            TaskList taskList = new TaskList(new TaskFileStorage());
            this.fulfillmentHandler = new FulfillmentHandler(userInputHandler,
                    userOutputHandler, taskList);
        } catch (DukeException de) {
            userOutputHandler.writeMessage(new Message(de.getMessage()));
        }
    }

    /**
     * Starts the execution of the CLI version of <code>Duke</code>.
     */
    public static void startCliDuke() {
        Duke duke = new Duke();
        if (duke.fulfillmentHandler != null) {
            duke.fulfillmentHandler.runCliChatBot();
        }
    }

    /**
     * Starts a JavaFx GUI version of the Duke chat bot.
     *
     * @param primaryStage window which user interacts with to use the chat bot.
     */
    @Override
    public void start(Stage primaryStage) {
        Label dukeTitle = new Label("Duke - Task manager at your service");
        dukeTitle.setFont(new Font("Helvetica", 25));

        TextField userCommandInputTextField = new TextField();
        userCommandInputTextField.promptTextProperty().set("Enter command here");

        TextArea userOutputTextArea = new TextArea();
        userOutputTextArea.setWrapText(true);
        userOutputTextArea.setEditable(false);

        Duke guiDuke = new Duke(userCommandInputTextField, userOutputTextArea);
        guiDuke.fulfillmentHandler.runGuiChatBotSetup();

        userCommandInputTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                guiDuke.fulfillmentHandler.handleGuiUserCommandInput();
                userCommandInputTextField.setText("");
            }
        });

        VBox vbox = new VBox(dukeTitle, userCommandInputTextField, userOutputTextArea);
        vbox.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(vbox);

        primaryStage.setTitle("Duke Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
