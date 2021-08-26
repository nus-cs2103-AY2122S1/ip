package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidDirectoryException;
import duke.exceptions.InvalidStorageFilePathException;

/**
 * Entry point for the Duke Application.
 * Application is initialised here and user interaction starts.
 */

public class Duke {

    /** deals with loading tasks from the file and saving tasks in the file.  */
    private Storage storage;

    /** contains the task list: has operations to add/delete tasks in the list.  */
    private TaskList tasks;

    /** deals with interactions with the user.  */
    private Ui ui;

    /**
     * Initialises the Duke object which will run the main program.
     * @param filePath user specified file path to store/load saved data
     */
    Duke(String filePath)  {
        this.ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (InvalidStorageFilePathException isfpe) {
            ui.showError(isfpe.getMessage());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /** Runs the program until termination condition is met upon parsing of Command.   */
    public void run() {
        this.ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidDirectoryException ide) {
                ui.showError(ide.getMessage());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasklist.txt").run();
    }

}
