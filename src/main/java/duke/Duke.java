package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.nio.file.Path;

/**
 * The Duke program. The input loop is abstracted here.
 */
public class Duke {
    /** Storage file path */
    public static final String OUTER_DIR = "data";
    public static final String FILE = "taskList.txt";

    /** Gui Variables */
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /** Instance variables */
    private final Storage myStorage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath The path used to store the tasks.
     */
    public Duke(Path filePath) {
        ui = new Ui();
        myStorage = new Storage(filePath);
        try {
            taskList = new TaskList(myStorage.load());
        } catch (DukeException err) {
            Ui.displayMessage(err.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String reply = command.execute(taskList, ui, myStorage);
            myStorage.updateTaskListToFile(taskList);
            return reply;
        } catch (DukeException err) {
            return err.getMessage();
        }
//        canContinue = !command.isExit();
    }

}
