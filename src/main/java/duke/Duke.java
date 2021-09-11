package duke;

import duke.gui.DukeDialogBox;
import duke.gui.DukeGui;
import duke.gui.UserDialogBox;
import duke.task.Task;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * A personal assistant chatbot that helps a person to keep track of various things.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public class Duke extends DukeGui implements duke.Parser {
    private static final String GREETING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String FAREWELL_MESSAGE = "I've saved your tasks.\nSee you soon! :)";
    private static final String FAREWELL_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String COMPLETE_TASK_COMMAND = "done";
    private static final String FIND_TASK_COMMAND = "find";
    private static final String DELETE_TASK_COMMAND = "delete";
    private static final String CREATE_TODO_COMMAND = "todo";
    private static final String CREATE_EVENT_COMMAND = "event";
    private static final String CREATE_DEADLINE_COMMAND = "deadline";

    private Storage storage;
    private TaskList taskList;

    /**
     * A constructor for Duke chatbot.
     */
    public Duke() {
        taskList = new TaskList();
        storage = new Storage(System.getProperty("user.dir") + "/duke_storage.txt");
        loadData();
    }


    /**
     * Starts the JavaFX GUI.
     * @param stage The primary stage that JavaFX provides
     */
    @Override
    public void start(Stage stage) {
        setUpGui(stage);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        greet();
    }

    /**
     * Passes user input to Duke and gets a corresponding response from Duke.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(taskMode(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new UserDialogBox(userText, new ImageView(user)),
                new DukeDialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Loads data that is saved in a given filename, and parses the data to load tasks.
     */
    public void loadData() {
        ArrayList<String> lines = storage.readLinesFromFile();
        for (int i = 0; i < lines.size(); i++) {
            Task task = Task.parseTaskFromSavedText(lines.get(i));
            taskList.addTask(task);
        }
    }

    /**
     * Saves Chatbot data to a given filename.
     */
    public void saveData() {
        String savedData = taskList.toSaveData();

        storage.overwriteNewFile();
        storage.writeToFile(savedData);
    }

    /**
     * Prints a greeting to the user.
     */
    public void greet() {
        Label dukeText = new Label(GREETING_MESSAGE);
        dialogContainer.getChildren().add(
                new DukeDialogBox(dukeText, new ImageView(duke))
        );
    }

    /**
     * Saves the user's existing data and informs the user.
     * @return A message informing the user that their data is saved.
     */
    public String endDuke() {
        saveData();
        return FAREWELL_MESSAGE;
    }

    /**
     * Handles the logic for managing a user's tasks.
     */
    public String taskMode(String msg) {
        if (msg.equals(FAREWELL_COMMAND)) {
            return endDuke();
        }
        try {
            if (msg.equals(LIST_COMMAND)) {
                return taskList.toString();
            } else if (msg.startsWith(COMPLETE_TASK_COMMAND)) {
                return taskList.completeTask(Parser.getIntFrom(COMPLETE_TASK_COMMAND, msg));
            } else if (msg.startsWith(FIND_TASK_COMMAND)) {
                return taskList.findTasks(Parser.getStringFrom(FIND_TASK_COMMAND, msg));
            } else if (msg.startsWith(DELETE_TASK_COMMAND)) {
                return taskList.deleteTask(Parser.getIntFrom(DELETE_TASK_COMMAND, msg));
            } else if (msg.startsWith(CREATE_TODO_COMMAND)) {
                return taskList.addNewTodo(Parser.getStringFrom(CREATE_TODO_COMMAND, msg));
            } else if (msg.startsWith(CREATE_EVENT_COMMAND)) {
                return taskList.addNewEvent(Parser.getStringFrom(CREATE_EVENT_COMMAND, msg));
            } else if (msg.startsWith(CREATE_DEADLINE_COMMAND)) {
                return taskList.addNewDeadline(Parser.getStringFrom(CREATE_DEADLINE_COMMAND, msg));
            } else {
                throw new DukeException("I don't know what that command means." +
                        "\nPlease input a valid command.");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
