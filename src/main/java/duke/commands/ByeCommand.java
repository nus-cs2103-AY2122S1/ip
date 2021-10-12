package duke.commands;

import duke.DukeException;
import duke.util.PersistentStorage;
import duke.util.Response;
import duke.util.Tasklist;

/**
 * A class encapsulating a "bye" command from the user.
 */
public class ByeCommand extends Command {

    public ByeCommand(){}

    /**
     * Executes the bye command by displaying the exit message and saving
     * stored tasks to PersistentStorage.
     *
     * @param taskList The Tasklist associated with the Duke instance.
     * @param response The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @throws DukeException if an error occured when saving tasks to PersistentStorage.
     * @returns A CommandResult containing the exit message and a boolean to terminate the program.
     */
    public CommandResult executeCommand(Tasklist taskList, Response response, PersistentStorage storage)
            throws DukeException {
        try {
            storage.saveTasks(taskList);
            return new CommandResult(response.showExitMsg(), true);

        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Returns a Boolean that tells Duke to terminate the program.
     *
     * @return True.
     */
    @Override
    public Boolean isExit() {
        return true;
    }
}
