package duke.command;

import duke.exception.DukeException;

import duke.taskTypes.Task;

import duke.util.Storage;
import duke.util.TaskList;


/**
 * Command that contains details when deleting a task
 */
public class DeleteCommand extends Command {
    private final String taskChosen;

    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     * @param taskChosen contains the string that describes which task to be deleted
     */
    public DeleteCommand(Storage storage, TaskList taskList, String taskChosen) {
        super(storage, taskList, false);
        this.taskChosen = taskChosen;
    }

    /**
     * Executes a set of instructions
     *
     * @return boolean To relay whether to continue the project
     * @throws DukeException
     */
    @Override
    public String exec() throws DukeException {
        Task deleteSuccess = taskList.delete(taskChosen);
        storage.saveUpdate(taskList);
        return taskDeleted(deleteSuccess);
    }

    private String taskDeleted(Task task) {
        String msg = task.toString();
        int taskLeft = taskList.taskLeft();
        String dukeAddedTask = "Noted. I've removed this task:\n "+ msg + "\nNow you have " + taskLeft + " tasks " +
                "in the list.";
        return dukeAddedTask;
    }
}
