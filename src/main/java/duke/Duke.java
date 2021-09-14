package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.CommandParser;
import duke.parser.GlobalParser;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.UiPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents the main Duke application.
 */
public class Duke extends Application {
    /**
     * The file path for storing task data
     */
    private static final String filePath = "duke.txt";
    /**
     * The storage for the application
     */
    private Storage storage;
    /**
     * The task list for the application
     */
    private TaskList taskList;
    /**
     * The UI for the application
     */
    private UiPane uiPane;
    /**
     * The parser for the commands.
     */
    private CommandParser<?> commandParser;

    /**
     * Constructs a Duke class.
     */
    public Duke() {
        this.storage = new Storage(filePath);
        this.uiPane = new UiPane(this);
        this.commandParser = new GlobalParser();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            uiPane.showErrorMessage(e.getMessage());
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Executes the command received by the UI.
     *
     * @param cmd The string command entered by the user.
     */
    public void executeCommand(String cmd) {
        try {
            Command command = commandParser.parse(cmd);
            command.execute(taskList, storage, uiPane);
        } catch (DukeException e) {
            uiPane.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Closes the Duke application.
     */
    public void closeApplication() {
        Platform.exit();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Duke");
        stage.setMinHeight(400);
        stage.setResizable(false);
        Scene scene = new Scene(uiPane);
        stage.setScene(scene);
        stage.show();
        uiPane.showMessage("Hello boss. What would you like to do today?");
        uiPane.showTaskList(taskList.getTasks());
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
