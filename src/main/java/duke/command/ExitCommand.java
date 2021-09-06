package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class ExitCommand extends Command {
    private static final boolean IS_EXIT = true;

    /**
     * Exits the bot.
     *
     * @param task The TaskList associated with the current bot.
     * @param userInt The User Interface associated with the current bot.
     * @param storage The storage associated with the current bot.
     * @return The string to be printed to the GUI.
     * @throws DukeException If any error has occurred during the addition of the task.
     */
    @Override
    public String execute(TaskList task, Ui userInt, Storage storage) {
        return userInt.exit();
    }

    /**
     * Gets the task associated with the command.
     *
     * @return null, as no task is associated with an exit command.
     */
    @Override
    public Task getTask() {
        return null;
    }

    /**
     * Returns a boolean determining whether the input should exit the bot.
     *
     * @return If the command exits the bot.
     */
    @Override
    public boolean isExit() {
        return this.IS_EXIT;
    }
}
