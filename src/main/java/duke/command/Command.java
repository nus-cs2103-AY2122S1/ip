package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

/**
 * Abstract command class that all commands inherits from
 */
public abstract class Command {
    /**
     * to denote when to exit the loop
     */
    public boolean isExit = false;

    /**
     * Executes commands
     * @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     * @throws IOException for commands that needs to write to storage file
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
}
