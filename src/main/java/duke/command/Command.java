package duke.command;

import java.io.IOException;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


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
     * @param ui current ui to access print responses
     * @param storage current storage
     * @throws IOException for commands that needs to write to storage file
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage, History history) throws IOException;
    public abstract String execute(TaskList taskList, ResponseFormatter rf,
                                   Storage storage, History history) throws IOException;
    public String undo(TaskList taskList, ResponseFormatter rf, Storage storage) throws IOException {
        return null;
    };
}
