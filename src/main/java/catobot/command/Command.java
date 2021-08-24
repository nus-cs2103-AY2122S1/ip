package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.exception.InvalidCommandException;
import catobot.item.TaskList;

/**
 * Represents a command from the user.
 */
public class Command implements Executable{
    /**
     * Executes the command given.
     *
     * @param tasks The list of tasks to be worked on.
     * @param ui The ui that responds to the user.
     * @param storage The storage of the tasks.
     * @throws BotException If the command is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        throw new InvalidCommandException();
    }

    /**
     * Check if the command is to exit.
     *
     * @return True if the command is to exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
