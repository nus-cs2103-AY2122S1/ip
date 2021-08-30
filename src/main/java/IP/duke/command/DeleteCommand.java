package IP.duke.command;

import IP.duke.task.Task;
import IP.duke.main.Storage;
import IP.duke.main.TaskList;
import IP.duke.main.Ui;

import java.util.ArrayList;

/**
 * Represents a command to delete a task.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class DeleteCommand extends Command {
    private int taskNumber;
    private boolean isExitCommand;

    /**
     * Class constructor.
     * 
     * @param taskNumber the serial number of the task.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        isExitCommand = false;
    }

    /**
     * Executes the command to delete a task.
     * 
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task DeletedTask = tasks.getTask(taskNumber - 1);
        ArrayList<Task> updatedTaskList = tasks.delete(taskNumber - 1);
        ui.showTaskDeleted(DeletedTask, updatedTaskList);
    }
    
}
