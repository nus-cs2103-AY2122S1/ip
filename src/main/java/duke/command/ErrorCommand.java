package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The DoneCommand class encapsulates an unrecognised command from the user.
 */
public class ErrorCommand extends Command {

    /**
     * Executes the response to the delete command from the user.
     *
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        String output = ui.showUnrecognised();
        return output;
    }
}
