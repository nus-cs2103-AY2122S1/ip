package duke.command;

import duke.util.Message;
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
     * @return message to be used by either the graphic UI or command line UI.
     */
    public abstract Message execute(TaskList taskList, Ui ui);
}
