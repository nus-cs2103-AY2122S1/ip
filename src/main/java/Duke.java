import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a chat bot request. A <code>Duke</code> object corresponds to
 * an instance of a chat bot.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs Duke Object.
     */
    public Duke() {
        String filePath = "data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs Duke Object.
     *
     * @param filePath a relative or absolute file path of text file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs chat box functions.
     * Reads user's commands (add, delete, exit) via console input.
     * If exit command, executes given commands upon task list and
     * displays output of commands on console.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Reads pre-written and user input commands.
     * Executes the stored commands and displays respective outputs.
     *
     * @param args command prompt / line arguments
     **/
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Retrieves chat bot's response based on user's command
     *
     * @param fullCommand user input.
     * @return Duke's chat bot message.
     * @throws DukeException if errors occur within list.
     */
    protected String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            String output = c.execute(tasks, ui, storage);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
