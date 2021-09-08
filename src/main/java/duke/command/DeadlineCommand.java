package duke.command;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the command equivalent of the deadline task.
 */
public class DeadlineCommand extends Command {
    protected static final String COMMAND = "deadline";

    private Deadline deadline;

    protected DeadlineCommand(String remainingText) throws DukeException {
        deadline = Deadline.createNewDeadline(remainingText, false);
    }

    /**
     * Executes the command.
     *
     * @param taskList The taskList keeping track of the tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert deadline != null : "deadline should not be null";
        return taskList.addTask(deadline);
    }
}
