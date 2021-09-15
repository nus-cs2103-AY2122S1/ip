package duke.command;

import duke.data.DukeException;
import duke.data.Storage;
import duke.data.InformationList;
import duke.data.Ui;

/**
 * Command that marks a task as done when executed.
 */
public class DoneCommand extends Command{
    /** Index of the task in InformationList. */
    private int taskNumber;

    /**
     * Constructs DoneCommand class.
     *
     * @param taskNumber Index of task in InformationList that is to be mark as done.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a task from InformationList as done.
     *
     * @param tasks The list of tasks that a user has.
     * @param ui The ui that sends a message to the user once the task is marked as done.
     * @param storage Saves the updated InformationList to disk.
     * @return The message produced by ui.
     */
    @Override
    public String execute(InformationList tasks, Ui ui, Storage storage) {
        if (taskNumber > tasks.getTasksSize() || taskNumber < 0) {
            throw new DukeException("Please insert a valid Task Number!");
        } else if (tasks.getTask(taskNumber).getIsDone()){
            throw new DukeException("Task has already been completed!");
        } else {
            tasks.markTaskAsDone(taskNumber);
            storage.save(tasks);
            return ui.showDoneTask();
        }
    }
}
