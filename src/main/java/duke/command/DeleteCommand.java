package duke.command;

import duke.data.DukeException;
import duke.data.Storage;
import duke.data.InformationList;
import duke.data.Ui;

/**
 * Command that deletes a Task from InformationList when executed.
 */
public class DeleteCommand extends Command {
    /** Index of the task in TaskList. */
    private int taskNumber;

    /**
     * Constructs DeleteCommand class.
     *
     * @param taskNumber Index of task in InformationList that is to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task from the InformationList.
     *
     * @param tasks The list of tasks that a user has.
     * @param ui The ui that sends a message to the user once the task is deleted.
     * @param storage Saves the updated TaskList to disk.
     * @return The message produced by ui.
     */
    @Override
    public String execute(InformationList tasks, Ui ui, Storage storage) {
        if (taskNumber > tasks.getTasksSize() || taskNumber <= 0) {
            throw new DukeException("Please insert a valid Task Number!");
        } else {
            tasks.deleteTask(taskNumber);
            storage.save(tasks);
            return ui.showDeletedInformation();
        }
    }
}
