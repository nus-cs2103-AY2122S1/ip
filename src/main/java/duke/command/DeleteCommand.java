package duke.command;
import duke.DukeException;
import duke.Input;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to delete tasks.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for DeleteCommand.
     *
     * @param input User input.
     * @throws DukeException If input is invalid.
     */
    public DeleteCommand(Input input) throws DukeException {
        if (input.hasCommandWordOnly("delete")) {
            throw new DukeException("A number must follow after the command word 'delete'.");
        }
        try {
            this.taskNumber = input.getIndex("delete");
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Please enter a valid task number.");
        }
    }

    /**
     * Removes task from current list.
     *
     * @param ls Current list.
     * @param ui Current Ui.
     * @param storage Current version of the saved tasks in the hard disk.
     * @throws DukeException If input is invalid.
     */
    @Override
    public String execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 0 || taskNumber >= ls.getSize()) {
            throw new DukeException("Item does not exist in the list.");
        }
        Task task = ls.getTask(taskNumber);
        ls.removeTask(taskNumber);
        storage.rewriteFile(ls);
        return ui.removeTaskFromList(task, ls.getSize());
    }

    /**
     * Signals to the system that the command is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
