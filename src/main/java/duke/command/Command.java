package duke.command;

import duke.DukeResponse;
import duke.Storable;
import duke.TaskList;
import duke.Ui;

/**
 * Command ensures that its derived classes have the execute method.
 */
public abstract class Command {
    /**
     * Executes a command and returns the output String.
     * Actions performed during execution depends on the command.
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Storage that command executes upon.
     * @return DukeResponse object containing the response string and boolean indicating whether response indicates an
     *         error.
     */
    public abstract DukeResponse execute(TaskList tasks, Ui ui, Storable storage);
}
