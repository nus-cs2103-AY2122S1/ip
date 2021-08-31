package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class DoneCommand extends Command {
    private boolean isExit = false;
    private int[] taskIds;
    private Task doneTask;

    /**
     * Public constructor of the DoneCommand object.
     * @param taskIds The id(s) of the task to be removed, with respect to
     *               the list printed by the list() command.
     */
    public DoneCommand(int... taskIds) {
        this.taskIds = taskIds;
    }

    /**
     * Marks a task in the TaskList of the bot as done.
     *
     * @param tasks The TaskList associated with the current bot.
     * @param userInt The User Interface associated with the current bot.
     * @param storage The storage associated with the current bot.
     * @return The string to be printed to the GUI.
     * @throws DukeException If any error has occurred during the addition of the task.
     */
    @Override
    public String execute(TaskList tasks, Ui userInt, Storage storage) throws DukeException {
        if (this.taskIds.length == 1) {
            doneTask = tasks.markDone(this.taskIds[0]);
            storage.save(tasks);
            return userInt.notifyDone(doneTask);
        } else {
            ArrayList<Task> doneTasks = new ArrayList<>();
            for (int taskId : this.taskIds) {
                doneTask = tasks.markDone(taskId);
                doneTasks.add(doneTask);
            }
            storage.save(tasks);
            return userInt.notifyMultiDone(doneTasks);
        }

    }

    /**
     * Gets the task associated with the command.
     *
     * @return The task associated with the given command.
     */
    @Override
    public Task getTask() {
        return this.doneTask;
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
