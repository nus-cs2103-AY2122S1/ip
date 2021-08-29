package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command to delete tasks.
 */
public class DeleteCommand extends Command {
    private final int taskNo;

    /**
     * Returns a new DeleteCommand object.
     * @param taskNo The number of the task to delete.
     * @throws DukeException If the task number is invalid.
     */
    public DeleteCommand(String taskNo) throws DukeException {
        try {
            this.taskNo = Integer.parseInt(taskNo);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Please enter a valid task number.");
        }
    }

    /**
     * Executes the command to delete a task.
     * @param tasks The list of tasks.
     * @param ui The Ui object.
     * @param storage The Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(taskNo);
    }

    /**
     * If the command is the exit command.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
