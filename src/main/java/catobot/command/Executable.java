package catobot.command;

import catobot.Storage;
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
     * @param storage The storage of the tasks.
     * @return The text to display.
     * @throws BotException If the command is invalid.
     */
    String execute(TaskList tasks, Storage storage) throws BotException;
}
