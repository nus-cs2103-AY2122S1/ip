package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * ListCommand class which displays the current tasks in the list.
 */
public class ListCommand extends Command {
    private boolean isExit = false;

    /**
     * Gets the user interface to display all the current tasks in the active
     * ArrayList.
     *
     * @param tasks The TaskList associated with the current bot.
     * @param userInt The User Interface associated with the current bot.
     * @param storage The storage associated with the current bot.
     * @return The string to be printed to the GUI.
     * @throws DukeException If any error has occurred during the addition of the task.
     */
    @Override
    public String execute(TaskList tasks, Ui userInt, Storage storage) {
        ArrayList<Task> taskArrList = tasks.getAllTasks();
        return userInt.notifyList(taskArrList);
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
