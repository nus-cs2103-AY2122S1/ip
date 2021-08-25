package duke.commands;

import duke.Tasklist;
import duke.DukeException;
import duke.UI;
import duke.PersistentStorage;

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
     * @param ui The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @throws DukeException if an error occured when saving tasks to PersistentStorage.
     */
    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage)
            throws DukeException {
        try {
            ui.showExitMsg();
            storage.saveTasks(taskList);
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
