package duke.command;

import static duke.util.Ui.INVALID_NUMBER;
import static duke.util.Ui.MISSING_DELETE_NUMBER_MESSAGE;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the delete command.
 */
public class DeleteCommand extends Command {
    protected static final String COMMAND = "delete";
    private String remainingText;

    protected DeleteCommand(String remainingText) {
        this.remainingText = remainingText;
    }

    /**
     * Executes the command.
     *
     * @param taskList The taskList keeping track of the tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            if (remainingText.isEmpty()) {
                throw new DukeException(MISSING_DELETE_NUMBER_MESSAGE);
            }

            int taskIndex = Integer.parseInt(remainingText);
            return taskList.deleteTask(taskIndex);
        } catch (NumberFormatException err) {
            throw new DukeException(INVALID_NUMBER);
        }
    }
}
