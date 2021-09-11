package duke;

import java.nio.file.Path;

import duke.command.Command;
import duke.gui.Main;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import javafx.application.Application;

/**
 * The Duke program. The input loop is abstracted here.
 */
public class Duke {
    /** Storage file path */
    public static final String OUTER_DIR = "data";
    public static final String FILE = "taskList.txt";

    /** Instance variables */
    private Storage myStorage;
    private TaskList taskList;
    private Ui ui;

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
            // Only time where error messages are displayed in the cli
            Ui.displayMessage(err.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Returns Duke's response as a String.
     *
     * @param input The user's input.
     * @return Duke's response as a String.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String reply = command.execute(taskList, ui, myStorage);
            myStorage.updateTaskListToFile(taskList);

            assert reply != null && !reply.equals("") : "Reply should not be blank";

            return reply;
        } catch (DukeException err) {
            return err.getMessage();
        }
    }

    /**
     * Closes the Scanner from Ui.
     */
    public void closeScanner() {
        this.ui.closeScanner();
    }

    /**
     * Launches the Duke application.
     *
     * @param args Arguments to be passed on to Main
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
