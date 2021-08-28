import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.command.Command;
import duke.task.TaskList;


/**
 * The Duke program is an application that can store the Tasks you need to do!
 */
public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for a Duke object.
     *
     * @param filePath the path to store the taskList.txt file in.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.wrapPrint(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * The method that runs the Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method that makes use of the run method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("data/taskList.txt").run();
    }
}
