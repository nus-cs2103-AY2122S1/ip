package duke.command;

import duke.Storable;
import duke.TaskList;
import duke.Ui;

/**
 * Command ensures that its derived classes have the execute method.
 */
public abstract class Command {
    /**
     * Executes a command.
     * Actions performed during execution depends on the command.
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Storage that command executes upon.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storable storage);
}
