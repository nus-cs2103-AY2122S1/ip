package duke.command;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
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
    public DeleteCommand(String input) throws DukeException {
        try {
            if (input.equals("delete") || input.equals("delete ")) {
                throw new DukeException("A number must follow after the command word 'delete'.");
            }
            this.taskNumber = Integer.valueOf(input.substring(7)) - 1;
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
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 0 || taskNumber >= ls.getSize()) {
            throw new DukeException("Item does not exist in the list.");
        }
        ls.removeTask(taskNumber);
        storage.rewriteFile(ls);
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
