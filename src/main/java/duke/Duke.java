package duke;

import duke.data.TaskFileStorage;
import duke.exceptions.DukeException;
import duke.fulfillment.FulfillmentHandler;
import duke.io.GuiUserInputHandler;
import duke.io.GuiUserOutputHandler;
import duke.io.UserInputHandler;
import duke.io.UserOutputHandler;
import duke.messages.Message;
import duke.tasks.TaskList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main class representing the Duke helper chat bot.
 *
 * @author kevin9foong
 */
public class Duke extends Application {
    private FulfillmentHandler fulfillmentHandler;

    /**
     * Default constructor for <code>Duke</code> with no arguments.
     */
    public Duke() {
    }

    /**
     * Constructs an instance of the <code>Duke</code> chat bot with the provided input and output handlers.
     *
     * @param userInputHandler  handles receiving user inputs from the input source.
     * @param userOutputHandler handles sending responses to the output destination.
     */
    public Duke(UserInputHandler userInputHandler, UserOutputHandler userOutputHandler) {
        try {
            TaskList taskList = new TaskList(new TaskFileStorage());
            this.fulfillmentHandler = new FulfillmentHandler(userInputHandler,
                    userOutputHandler, taskList);
        } catch (DukeException de) {
            userOutputHandler.writeMessage(new Message(de.getMessage()));
        }
    }

    /**
     * Builds the GUI and starts the execution of the JavaFX GUI version
     * of the Duke chat bot.
     *
     * @param primaryStage window which user interacts with to use the chat bot.
     */
    @Override
    public void start(Stage primaryStage) {
        // title
        Label dukeTitle = new Label("Duke - Task manager at your service");
        dukeTitle.setFont(new Font("Helvetica", 25));

        // command input text field
        TextField userCommandInputTextField = new TextField();
        userCommandInputTextField.promptTextProperty().set("Enter command here");

        // Add Task commands
        ComboBox<String> taskTypeComboBox = new ComboBox<>();
        TextField descriptionField = new TextField();
        TextField taskSpecificField = new TextField();

        descriptionField.promptTextProperty().set("Description");
        taskSpecificField.promptTextProperty().set("Not Applicable");
        taskSpecificField.setEditable(false);

        String toDoOption = "ToDo";
        String eventOption = "Event";
        String deadlineOption = "Deadline";
        taskTypeComboBox.getItems().addAll(toDoOption, deadlineOption, eventOption);
        taskTypeComboBox.setValue("ToDo");
        taskTypeComboBox.setOnAction(event -> {
            if (taskTypeComboBox.getValue().equals(deadlineOption)) {
                taskSpecificField.setEditable(true);
                taskSpecificField.promptTextProperty().set("Deadline");
            } else if (taskTypeComboBox.getValue().equals(eventOption)) {
                taskSpecificField.setEditable(true);
                taskSpecificField.promptTextProperty().set("Venue");
            } else {
                taskSpecificField.setEditable(false);
                taskSpecificField.promptTextProperty().set("Not Applicable");
            }
        });

        Button addTaskButton = new Button("Add Task");
        HBox addTasksFields = new HBox(taskTypeComboBox, descriptionField, taskSpecificField, addTaskButton);

        // list command
        Button listButton = new Button("List Tasks");

        // delete command
        TextField taskNumberToDeleteField = new TextField();
        taskNumberToDeleteField.promptTextProperty().set("Task number");
        Button deleteButton = new Button("Delete Task");
        HBox deleteFields = new HBox(taskNumberToDeleteField, deleteButton);

        // mark done command
        TextField taskNumberToMarkDoneField = new TextField();
        taskNumberToMarkDoneField.promptTextProperty().set("Task number");
        Button markDoneButton = new Button("Mark Task Done");
        HBox markDoneFields = new HBox(taskNumberToMarkDoneField, markDoneButton);

        // find command
        TextField searchQueryField = new TextField();
        searchQueryField.promptTextProperty().set("Search query");
        Button findButton = new Button("Find Task");
        HBox searchFields = new HBox(searchQueryField, findButton);

        // A-Varargs: usage of VarArgs here as the constructor of VBox and HBox uses VarArgs.
        VBox commandsLayout = new VBox(addTasksFields, listButton, markDoneFields, deleteFields, searchFields);
        commandsLayout.setAlignment(Pos.TOP_LEFT);

        // response output text area
        TextArea userOutputTextArea = new TextArea();
        userOutputTextArea.setWrapText(true);
        userOutputTextArea.setEditable(false);

        UserInputHandler userInputHandler = new GuiUserInputHandler(userCommandInputTextField);
        UserOutputHandler userOutputHandler = new GuiUserOutputHandler(userOutputTextArea);

        Duke guiDuke = new Duke(userInputHandler, userOutputHandler);

        if (guiDuke.fulfillmentHandler != null) {
            guiDuke.fulfillmentHandler.runGuiChatBotSetup();

            addTaskButton.setOnAction(event -> {
                if (taskTypeComboBox.getValue().equals(toDoOption)) {
                    userCommandInputTextField.setText("todo " + descriptionField.getText());
                } else if (taskTypeComboBox.getValue().equals(deadlineOption)) {
                    userCommandInputTextField.setText("deadline " + descriptionField.getText()
                            + " /by " + taskSpecificField.getText());
                } else if (taskTypeComboBox.getValue().equals(eventOption)) {
                    userCommandInputTextField.setText("event " + descriptionField.getText()
                            + " /at " + taskSpecificField.getText());
                }
                guiDuke.fulfillmentHandler.handleGuiUserCommandInput();
                descriptionField.setText("");
                taskSpecificField.setText("");
                userCommandInputTextField.setText("");
            });

            listButton.setOnAction(event -> {
                userCommandInputTextField.setText("list");
                guiDuke.fulfillmentHandler.handleGuiUserCommandInput();
                userCommandInputTextField.setText("");
            });

            deleteButton.setOnAction(event -> {
                userCommandInputTextField.setText("delete " + taskNumberToDeleteField.getText());
                guiDuke.fulfillmentHandler.handleGuiUserCommandInput();
                taskNumberToDeleteField.setText("");
                userCommandInputTextField.setText("");
            });

            markDoneButton.setOnAction(event -> {
                userCommandInputTextField.setText("done " + taskNumberToMarkDoneField.getText());
                guiDuke.fulfillmentHandler.handleGuiUserCommandInput();
                taskNumberToMarkDoneField.setText("");
                userCommandInputTextField.setText("");
            });

            findButton.setOnAction(event -> {
                userCommandInputTextField.setText("find " + searchQueryField.getText());
                guiDuke.fulfillmentHandler.handleGuiUserCommandInput();
                searchQueryField.setText("");
                userCommandInputTextField.setText("");
            });

            userCommandInputTextField.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    guiDuke.fulfillmentHandler.handleGuiUserCommandInput();
                    userCommandInputTextField.setText("");
                }
            });
        }

        VBox vbox = new VBox(dukeTitle, userCommandInputTextField,
                commandsLayout, userOutputTextArea);
        vbox.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(userOutputTextArea, Priority.ALWAYS);

        Scene scene = new Scene(vbox);

        primaryStage.setTitle("Duke Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
