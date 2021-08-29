package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command to mark a task as done.
 */
public class DoneCommand extends Command {
    private final int taskNo;

    /**
     * Returns a new DoneCommand object.
     * @param taskNo The number of the task to mark as done.
     * @throws DukeException If the task number is invalid.
     */
    public DoneCommand(String taskNo) throws DukeException {
        try {
            this.taskNo = Integer.parseInt(taskNo);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Please enter a valid task number.");
        }
    }

    /**
     * Executes the command to mark the task as done.
     * @param tasks The list of tasks.
     * @param ui The Ui object.
     * @param storage The Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.taskDone(taskNo);
    }

    /**
     * If the command is the exit command.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
