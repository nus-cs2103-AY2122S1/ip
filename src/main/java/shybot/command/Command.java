package shybot.command;

import shybot.ShyBot;
import shybot.Storage;
import shybot.exception.ShyBotException;
import shybot.task.TaskList;

/**
 * Command class encapsulates the command input by user.
 */
public abstract class Command {
    /**
     * @param tasks   Task list used to store the tasks.
     * @param storage Storage used to deal with making sense of the user command.
     * @throws ShyBotException If the command is erroneous.
     */
    public abstract void execute(ShyBot shybot, TaskList tasks, Storage storage)
        throws ShyBotException;

    /**
     * Returns true if this command is an ExitCommand.
     *
     * @return true if this command is an ExitCommand.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
