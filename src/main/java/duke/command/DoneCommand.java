package duke.command;

import duke.exception.TaskAlreadyDoneException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This DoneCommand class represents a command to mark a task as done.
 */
public class DoneCommand extends Command {

    private int taskId;

    /**
     * Constructor for a DoneCommand instance that takes in a task id.
     *
     * @param taskId The position of the task in the task list.
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Marks a task as done and updates the hard disk of the change.
     *
     * @param tasks The task list.
     * @param ui The UI of the application.
     * @param storage The storage system of the application.
     * @throws TaskIndexOutOfBoundsException If the task list is accessed with an illegal index.
     * @throws TaskAlreadyDoneException If the task has already been marked as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexOutOfBoundsException,
            TaskAlreadyDoneException {
        // Marks the task as done
        Task completedTask = tasks.markAsDone(this.taskId);

        // Displays a message indicating the task has been successfully marked as done
        ui.showCommandDone("Nice! I've marked this task as done:\n  " + completedTask);

        // Saves the current task list to the hard drive
        storage.save(tasks);
    }

    /**
     * Indicates that this command does not intend to exit the system.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
