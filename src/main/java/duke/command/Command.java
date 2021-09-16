package duke.command;

import java.io.IOException;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;


/**
 * Abstract command class that all commands inherits from
 */
public abstract class Command {
    /**
     * to denote when to exit the loop
     */
    protected boolean isExit = false;

    /**
     * get the exit check
     * @return exit boolean
     */
    public boolean getExit() {
        return this.isExit;
    }

    /**
     * Executes commands
     *
     * @param taskList current list
     * @param rf response formatter
     * @param storage current storage
     * @return String formatted string response
     * @throws IOException for commands that needs to write to storage file
     */
    public abstract String execute(TaskList taskList, ResponseFormatter rf,
                                   Storage storage, History history) throws IOException;
    public String undo(TaskList taskList, ResponseFormatter rf, Storage storage) throws IOException {
        return null;
    };
}
