import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.command.Command;
import duke.task.TaskList;

/**
 * This class represents Duke who runs the main program.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath filePath to the save file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = this.storage.load();

    }

    /**
     * Runs the program.
     */
    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (Exception e) {

            } finally {
                Ui.showLine();
            }
        }
    }

    /**
     * Main method which starts the program by calling run().
     *
     * @param args main method parameters.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}


