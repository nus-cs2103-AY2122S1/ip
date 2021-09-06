package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;



/**
 * AddCommand class which indicates input is adding new tasks.
 */
public class AddCommand extends Command {
    private final boolean IS_EXIT = false;
    private Task newTask;

    /**
     * Public constructor for the AddCommand.
     * @param newTask The task to be added.
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Adds the task into the respective components of the bot.
     *
     * @param tasks The TaskList associated with the current bot.
     * @param userInt The User Interface associated with the current bot.
     * @param storage The storage associated with the current bot.
     * @throws DukeException If any error has occurred during the addition of the task.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui userInt, Storage storage) throws DukeException {
        tasks.add(this.newTask);
        ArrayList<Task> taskArrList = tasks.getAllTasks();
        storage.save(tasks);
        return userInt.notifyAdd(taskArrList);
    }

    /**
     * Gets the task associated with the command.
     *
     * @return The task associated with the given command.
     */
    @Override
    public Task getTask() {
        return this.newTask;
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
