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
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     * @param taskNumber contains the string that describes which task to be deleted
     */
    public DeleteCommand(Storage storage, TaskList taskList, String taskNumber) {
        super(storage, taskList, false);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes a set of instructions
     *
     * @return boolean To relay whether to continue the project
     * @throws DukeException
     */
    @Override
    public String exec() throws DukeException {
        Task deleteSuccess = taskList.delete(taskNumber);
        storage.saveUpdate(taskList);
        return taskDeleted(deleteSuccess);
    }

    /**
     * Formats a successful delete message
     *
     * @param task
     * @return String successful delete message
     */
    private String taskDeleted(Task task) {
        String msg = task.toString();
        int taskLeft = taskList.taskLeft();
        String dukeAddedTask = "Noted. I've removed this task:\n " + msg
                + "\nNow you have " + taskLeft + " tasks " + "in the list.";

        return dukeAddedTask;
    }
}
