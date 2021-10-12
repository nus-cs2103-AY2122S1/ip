package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.util.Parser;
import duke.util.PersistentStorage;
import duke.util.Response;
import duke.util.Tasklist;

/**
 * Class that encapsulates the Duke application
 */
public class Duke {

    private boolean isSuccessfulFileLoad;
    private PersistentStorage storage;
    private Tasklist taskList;
    private Response response;

    /**
     * Public constructor for a Duke object
     *
     * @param filePath A filepath to a text file that serves as persistent storage for Duke.
     */
    public Duke(String filePath) {
        response = new Response();
        storage = new PersistentStorage(filePath);

        try {
            taskList = storage.loadTasks();
            this.isSuccessfulFileLoad = true;
        } catch (DukeException e) {
            this.isSuccessfulFileLoad = false;
            taskList = new Tasklist();
        }
    }

    /**
     * Executes a command from the user and gets the result of executing that command.
     *
     * @param input A String representing the input command by the user.
     * @return A CommandResult encapsulating the result of executing the command provided by the user.
     */
    public CommandResult getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            CommandResult output = command.executeCommand(taskList, response, storage);
            return output;
        } catch (DukeException e) {
            return new CommandResult(response.showErrorMsg(e));
        }
    }

    /**
     * Returns a boolean indicating if persistent storage file was successfully loaded
     *
     * @return true if persistent storage file was loaded successfully, and false otherwise.
     */
    public boolean hasLoadedFile() {
        return isSuccessfulFileLoad;
    }
}
