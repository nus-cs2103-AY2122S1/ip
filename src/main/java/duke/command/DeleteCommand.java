package duke.command;

import duke.exception.DukeException;
import duke.taskTypes.Task;
import duke.util.Storage;
import duke.util.TaskList;


/**
 * Command that deletes task from tasklist
 */
public class DeleteCommand extends Command {
    private final String taskNumber;

    /**
     * Basic Constructor
     *
     * @param storage StorageTxt object to save
     * @param taskList Tasklist to add task to
     * @param taskNumber contains the string that describes which task to be deleted
     */
    public DeleteCommand(Storage storage, TaskList taskList, String taskNumber) {
        super(storage, taskList, false);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes a set of instructions to delete a task from list and storage
     *
     * @return String success message
     * @throws DukeException
     */
    @Override
    public String exec() throws DukeException {
        Task deletedTask = taskList.delete(taskNumber);
        storage.saveUpdateTask(taskList);
        return successfullyDeletedTask(deletedTask);
    }

    /**
     * Returns a string success message
     *
     * @param task
     * @return
     */
    private String successfullyDeletedTask(Task task) {
        String taskDetails = task.toString();
        int taskLeft = taskList.taskLeft();
        return successMessage(taskDetails, taskLeft);
    }

    /**
     * Formats a successful delete message
     *
     * @return String successful delete message
     */
    private String successMessage(String taskDetails, int taskLeft) {
        return "Noted. I've removed this task:\n " + taskDetails
                + "\nNow you have " + taskLeft + " tasks " + "in the list.";
    }
}
