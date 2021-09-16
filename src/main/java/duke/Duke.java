package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidDirectoryException;
import duke.exceptions.InvalidStorageFilePathException;

/**
 * Models a custom CLI guided Chatbot for task tracking
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

        assert filePath != null : "Filepath is not specified";

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

    /**
     * Parses the user input to get a display response for the GUI
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException | InvalidDirectoryException e) {
            return e.getMessage();
        }
    }

    /**
     * Displays this startup message upon initialization of the app
     */
    public String showOpeningMessage() {
        return "Hello! This is Duke :)\n"
                + "Now, what can I do for you?";
    }

}
