package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to mark tasks as done.
 */
public class DoneCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for DoneCommand.
     *
     * @param input User input.
     * @throws DukeException If input is invalid.
     */
    public DoneCommand(String input) throws DukeException {
        try {
            if (input.equals("done") || input.equals("done ")) {
                throw new DukeException("A number must follow after the command word 'done'.");
            }
            this.taskNumber = Integer.valueOf(input.substring(5)) - 1;;
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Please enter a valid task number.");
        }
    }

    /**
     * Marks item as done.
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
        Task task = ls.getTask(taskNumber);
        task.setDone();
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
