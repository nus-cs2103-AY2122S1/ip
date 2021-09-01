package catobot.command;

import catobot.Storage;
import catobot.exception.BotException;
import catobot.exception.InvalidCommandException;
import catobot.item.TaskList;

/**
 * Represents a command from the user.
 */
public class Command implements Executable {
    /**
     * Executes the command given.
     *
     * @param tasks The list of tasks to be worked on.
     * @param storage The storage of the tasks.
     * @return The text to display.
     * @throws BotException If the command is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BotException {
        throw new InvalidCommandException();
    }
}
