package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;

public class ExitCommand extends Command {
    private boolean isExit = true;

    /**
     * Exits the bot.
     *
     * @param task The TaskList associated with the current bot.
     * @param userInt The User Interface associated with the current bot.
     * @param storage The storage associated with the current bot.
     * @throws DukeException If any error has occurred during the addition of the task.
     */
    @Override
    public void execute(TaskList task, UI userInt, Storage storage) {
        userInt.exit();
    }

    /**
     * Gets the task associated with the command.
     *
     * @return null, as task is associated with an exit command.
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
    public boolean isExit() {
        return this.isExit;
    }
}
