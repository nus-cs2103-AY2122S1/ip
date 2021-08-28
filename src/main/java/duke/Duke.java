package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke program implements a bot with a set of simple commands
 * Initializes the app, and interacts with the user.
 */
public class Duke {

    /**
     * The tasks associated with the user
     **/
    private TaskList tasks;

    /**
     * The storage location associated with the user
     **/
    private Storage storage;

    /**
     * The ui interacting with the user
     **/
    private Ui ui;

    /**
     * Initializes the duke program with a given filePath.
     *
     * @param filePath The path of the file containing your list of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot until the user inputs the bye command.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printBorder();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.write(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printBorder();
            }
        }
    }

    /**
     * Runs the program
     **/
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
