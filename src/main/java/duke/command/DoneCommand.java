package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    private boolean isExit = false;
    private int taskid;
    private Task doneTask;

    /**
     * Public constructor of the DoneCommand object.
     * @param taskid The id of the task to be removed, with respect to
     *               the list printed by the list() command.
     */
    public DoneCommand(int taskid) {
        this.taskid = taskid;
    }

    /**
     * Marks a task in the TaskList of the bot as done.
     *
     * @param tasks The TaskList associated with the current bot.
     * @param userInt The User Interface associated with the current bot.
     * @param storage The storage associated with the current bot.
     * @throws DukeException If any error has occurred during the addition of the task.
     */
    @Override
    public void execute(TaskList tasks, UI userInt, Storage storage) throws DukeException {
        doneTask = tasks.markDone(this.taskid);
        userInt.notifyDone(doneTask);
        storage.save(tasks);
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
