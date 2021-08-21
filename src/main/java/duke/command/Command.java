package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * Interface for commands.
 */
public interface Command {
    /**
     * Main codes to run for the chat.
     *
     * @param taskList duke.main.TaskList to execute the command.
     * @param ui To interact with the user.
     */
    public abstract void execute(TaskList taskList, Ui ui);
}