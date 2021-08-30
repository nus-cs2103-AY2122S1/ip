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

    /**
     * Initialises the Duke object which will run the main program.
     *
     * @param filePath user specified file path to store/load saved data
     */
    Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (InvalidStorageFilePathException isfpe) {
            tasks = new TaskList();
            System.out.println(isfpe.getMessage());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException | InvalidDirectoryException e) {
            return e.getMessage();
        }
    }

    public String showOpeningMessage() {
        return "Hello! This is Duke :)\n"
                + "To use my AUTOSAVE feature, please type 'bye' when you're done!\n"
                + "Otherwise, I am unable to save your tasks for future reference :(\n"
                + "Now, what can I do for you?";
    }

}
