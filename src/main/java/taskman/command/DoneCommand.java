package taskman.command;

import taskman.exception.DukeException;
import taskman.tasktypes.Task;
import taskman.util.Storage;
import taskman.util.TaskList;

/**
 * Command that sets the task as done
 */
public class DoneCommand extends Command {

    private final String taskChosen;
    private final String successMessage = "Nice! I've marked this task as done:\n";

    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     * @param taskChosen contains the string that describes which task to be declared done
     */
    public DoneCommand(Storage storage, TaskList taskList, String taskChosen) {
        super(storage, taskList, false);
        this.taskChosen = taskChosen;
    }

    /**
     * Executes a set of instructions to set a task as done
     *
     * @return String success message
     * @throws DukeException
     */
    @Override
    public String exec() throws DukeException {
        Task doneTask = taskList.done(taskChosen);
        storage.saveUpdateTask(taskList);
        return successfullyCompletedTask(doneTask);
    }

    /**
     * Formats the done success message
     *
     * @param task
     * @return
     */
    private String successfullyCompletedTask(Task task) {
        String taskDetails = task.toString();
        return successMessage + taskDetails;
    }

}
