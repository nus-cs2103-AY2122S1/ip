package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * Interface for commands.
 *
 * @author marcuspeh
 * @version A-JavaDoc
 * @since 23 Aug 2021
 */
public interface Command {
    /**
     * Execute the command for duke.
     *
     * @param taskList duke.main.TaskList to execute the command.
     * @param ui To interact with the user.
     */
    public abstract void execute(TaskList taskList, Ui ui);
}
