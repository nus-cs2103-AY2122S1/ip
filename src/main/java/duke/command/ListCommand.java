package duke.command;

import static duke.util.Ui.LIST_MESSAGE;
import static duke.util.Ui.NO_TASKS_IN_LIST_MESSAGE;
import static duke.util.Ui.TOO_MANY_ARGUMENTS_LIST_MESSAGE;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the list command.
 */
public class ListCommand extends Command {
    protected static final String COMMAND = "list";
    private String remainingText;

    protected ListCommand(String remainingText) {
        this.remainingText = remainingText;
    }

    /**
     * Executes the command.
     *
     * @param taskList The taskList keeping track of the tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     * @throws DukeException The exception to be thrown.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (!remainingText.isEmpty()) {
            throw new DukeException(TOO_MANY_ARGUMENTS_LIST_MESSAGE);
        }
        if (taskList.getSize() == 0) {
            return NO_TASKS_IN_LIST_MESSAGE;
        } else {
            return String.format(LIST_MESSAGE, taskList);
        }
    }
}
