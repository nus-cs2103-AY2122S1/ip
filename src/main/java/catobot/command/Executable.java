package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.item.TaskList;

/**
 * Represents the interface of an executable object.
 */
public interface Executable {

    /**
     * Executes the command.
     *
     * @param tasks The list of tasks to be worked on.
     * @param ui The ui that responds to the user.
     * @param storage The storage of the tasks.
     * @throws BotException If the command is invalid.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws BotException;
}
