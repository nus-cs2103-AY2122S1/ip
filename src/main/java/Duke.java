import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class that kick starts Duke.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for initialising Duke.
     * @param filePath the path to load the list of tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();

        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts Duke running.
     */
    public void run() {
        ui.showWelcome(tasks);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = new Parser().parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main function to initialise Duke.
     * @param args None required
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
