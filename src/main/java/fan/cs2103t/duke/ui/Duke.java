package fan.cs2103t.duke.ui;

import fan.cs2103t.duke.command.Command;
import fan.cs2103t.duke.command.ExitCommand;
import fan.cs2103t.duke.exception.DukeException;
import fan.cs2103t.duke.parser.Parser;
import fan.cs2103t.duke.storage.Storage;
import fan.cs2103t.duke.task.TaskList;

/**
 * Implementation of Duke, a personal assistant chatterbot that
 * helps a person to keep track of various things.
 */
public class Duke {

    private Storage storage;
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Empty constructor.
     */
    public Duke() {}

    /**
     * Initializes Duke with the specified file path.
     * <p>
     * Initializes the UI manager, the storage manager, and the parser.
     * Duke's data will be stored in the file denoted by the specified file path.
     *
     * @param filePath the specified file path to store Duke's data.
     * @throws DukeException if there occurs any error during initialization.
     */
    public void initializeDukeWithStoragePath(String filePath) throws DukeException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = storage.retrieveTaskList();
        } catch (DukeException e) {
            tasks = new TaskList();
            storage.saveTaskList(tasks);
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Gets and returns Duke's response message for every input.
     * Terminates the current program immediately if the specified command is an exit command.
     *
     * @param input the one-line input entered by users.
     * @return a response message for the specific input.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command command = parser.parseCommand(input);
            String tmp = command.execute(tasks, ui);
            output = tmp == null ? "NULL" : tmp;
            storage.saveTaskList(tasks); // save the latest task list no matter what command it is
            if (ExitCommand.isExit(command)) {
                System.exit(0); // TODO: better handle this
            }
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }

}
