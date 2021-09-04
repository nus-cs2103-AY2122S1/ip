package duke.command;

import duke.data.DukeException;
import duke.data.Storage;
import duke.data.TaskList;
import duke.data.Ui;

/**
 * Command that marks a task as done when executed.
 */
public class DoneCommand extends Command{
    /**
     * Index of the task in TaskList
     */
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a task from Tasklist as done.
     *
     * @param tasks The list of tasks that a user has
     * @param ui The ui that sends a message to the user once the task is marked as done
     * @param storage Saves the updated TaskList to disk
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber > tasks.getSize() || taskNumber < 0) {
            throw new DukeException("Please insert a valid task.Task Number!");
        } else {
            tasks.markTaskAsDone(taskNumber);
            storage.save(tasks);
            return "Task marked as done!";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
