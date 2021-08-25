package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;
import java.util.ArrayList;

public class ListCommand extends Command {
    private boolean isExit = false;

    /**
     * Gets the user interface to display all the current tasks in the active
     * ArrayList.
     *
     * @param tasks The TaskList associated with the current bot.
     * @param userInt The User Interface associated with the current bot.
     * @param storage The storage associated with the current bot.
     * @throws DukeException If any error has occurred during the addition of the task.
     */
    @Override
    public void execute(TaskList tasks, UI userInt, Storage storage) {
        ArrayList<Task> taskArrList = tasks.getAllTasks();
        userInt.notifyList(taskArrList);
    }

    /**
     * Gets the task associated with the command.
     *
     * @return null, as no single task is associated with the list command.
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
