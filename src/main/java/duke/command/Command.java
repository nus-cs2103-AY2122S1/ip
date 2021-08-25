package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Abstract class which determines the behaviour of the bot
 * in response to the input of the user.
 */
public abstract class Command {
    /**
     * Abstract method which executes the commands.
     *
     * @param task The TaskList associated with the current bot.
     * @param userInt The User Interface associated with the current bot.
     * @param storage The storage associated with the current bot.
     * @throws DukeException If any error has occurred during the execution of the commands.
     */
    public abstract void execute(TaskList task, UI userInt, Storage storage) throws DukeException;


    /**
     * Returns a boolean determining whether the input should exit the bot.
     *
     * @return If the command exits the bot.
     */
    public abstract boolean isExit();

    /**
     * Gets the task associated with the command.
     *
     * @return The task associated with the given command.
     */
    public abstract Task getTask();
}
