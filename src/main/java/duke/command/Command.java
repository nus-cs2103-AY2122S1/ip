package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The command class encapsulates an executable command by Duke.
 */
public abstract class Command {
    /**
     * The abstract method to execute the correct response to the user's command.
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     */
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws IOException, DukeException;

    /**
     * Indicates whether the user is still going to use Duke.
     * @return False to indicate that the user has no yet stopped using Duke.
     */
    public boolean isExit() {
        return false;
    }
}
