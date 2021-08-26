package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.Ui;
import duke.task.Storage;
import duke.task.Task;

/**
 * Represents a Command that deletes a Task from the TaskList.
 */
public class DeleteCommand extends Command {
    /**
     * The task number to be deleted.
     */
    private Integer taskNum;

    /**
     * Constructs a delete command with a task number.
     * @param taskNum The task number to be deleted.
     */
    public DeleteCommand(Integer taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the delete task command.
     *
     * @param tasks The task list to execute the command on.
     * @param ui The user interface.
     * @param storage The storage for the tasks.
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskNum);

        ui.showMessage("Ok, I've deleted this task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.getListSize() + " tasks in the list.");

        storage.save(tasks.getListData());
    }

    /**
     * Returns false to continue the program.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}