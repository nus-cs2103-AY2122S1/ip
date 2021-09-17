package duke.command;

import java.io.IOException;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;


/**
 * Abstract command class that all commands inherits from.
 */
public abstract class Command {
    /**
     * To denote when to exit the loop.
     */
    protected boolean isExit = false;

    /**
     * Get the exit boolean for checking.
     *
     * @return Exit boolean
     */
    public boolean getExit() {
        return this.isExit;
    }

    /**
     * Executes commands.
     *
     * @param taskList Current list
     * @param rf Response formatter
     * @param storage Current storage
     * @param history List of previous commands
     * @return String formatted string response
     * @throws IOException For commands that needs to write to storage file
     */
    public abstract String execute(TaskList taskList, ResponseFormatter rf,
                                   Storage storage, History history) throws IOException;

    public String undo(TaskList taskList, ResponseFormatter rf, Storage storage) throws IOException {
        return null;
    };
}
