package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Ui;
import duke.util.Parser;
import duke.util.Storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import static duke.util.Ui.EXIT_MESSAGE;
import static duke.util.Ui.REWELCOME_MESSAGE;
import static duke.util.Ui.WELCOME_MESSAGE;

/**
 * The Duke program. The input loop is abstracted here.
 */
public class Duke {
    /** Storage file path */
    private static final String OUTER_DIR = "data";
    private static final String FILE = "taskList.txt";

    /** Instance variables */
    private final Storage myStorage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * The input loop. Handles user input, creates tasks and outputs messages accordingly.
     */
    private void inputLoop() {
        boolean canContinue = true;
        while (canContinue) {
            String input = ui.readCommand();
            try {
                Command command = Parser.parse(input);
                command.execute(taskList, ui, myStorage);
                myStorage.updateTaskListToFile(taskList);
                canContinue = !command.isExit();
            } catch (DukeException err) {
                Ui.displayMessage(err.getMessage());
            }
        }
    }

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
     *
     */
    public void run() {
        if (!myStorage.didTaskFileExist()) {
            Ui.displayMessage(WELCOME_MESSAGE);
        } else {
            Ui.displayMessage(REWELCOME_MESSAGE);
        }
        myStorage.readTaskFile(taskList);
        inputLoop();
        ui.closeScanner();
        Ui.displayMessage(EXIT_MESSAGE);
    }

    public static void main(String[] args) {
        new Duke(Paths.get(OUTER_DIR, FILE)).run();
    }
}
