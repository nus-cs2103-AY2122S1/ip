package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The command class encapsulates an executable command by Duke.
 */
public abstract class Command {

    /**
     * The abstract method to execute the correct response to the user's command.
     *
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     * @return The String to be printed in the Duke GUI.
     * @throws IOException If there is an exception relating to the input and output.
     */
    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws IOException;

    /**
     * Indicates whether the user is still going to use Duke.
     *
     * @return False to indicate that the user has no yet stopped using Duke.
     */
    public boolean isExit() {
        return false;
    }
}
