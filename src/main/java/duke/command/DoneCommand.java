package duke.command;

import duke.*;
import duke.io.ResponseManager;
import duke.task.Task;
import duke.task.TaskManager;

/**
 * This class encapsulates the command to complete a task.
 * It is triggered by the parser and uses the TaskManager, Ui, and Storage.
 */
public class DoneCommand implements ICommand {

    private String taskIndex;
    private TaskManager tm;
    private ResponseManager responseManager;
    private Storage storage;
    private Task completedTask;
    private String reply;

    /**
     * Constructor for the command.
     *
     * @param taskIndex The index of the task which the user wants to mark completed.
     */
    public DoneCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the desired task completed by interacting with the relevant instances as mentioned above.
     *
     * @param tm The TaskManager object controlling the tasks in Duke.
     * @param responseManager The Ui object managing Duke's user interface.
     * @param storage The Storage object managing the local storing of tasks.
     */
    public void execute(TaskManager tm, ResponseManager responseManager, Storage storage) {
        try {
            completedTask = tm.completeTask(taskIndex);
            this.responseManager = responseManager;
            this.tm = tm;
            this.storage = storage;
            if (completedTask == null) {
                reply = responseManager.getInvalidIndexMessage();
            } else {
                reply = responseManager.getTaskCompletionMessage(completedTask, tm.getTasks().size());
                storage.updateSave(tm.getTasks());
            }
        } catch (DukeException.InvalidInputException e) {
            reply = responseManager.getErrorMessage(e);
        }
    }

    public String getReply() {
        return reply;
    }
}
