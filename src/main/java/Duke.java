import command.Command;
import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Project Duke is an educational software project.
 * It stores tasks entered by users locally and reloads then on every start-up.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates the instance of Duke.
     * The path to the local file and tasklist will be initialised.
     *
     * @param filePath the path to the local file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke program.
     * Programme will ask for input until user enters the 'bye' command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Command.parse(fullCommand);
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
     * Creates the initialization process and run the programme when completed.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
