package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyTaskListDukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * A ListCommand class that extends from the Command class.
 *
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class ListCommand extends Command{

    private static final String SUCCESS_MESSAGE = "Here is your task list:\n";

    /**
     * An empty constructor to initialize an exit command.
     */
    public ListCommand() {
    }

    /**
     * Method to execute a list command.
     * @param taskList The task list to execute the command on.
     * @param storage The place to store the session.
     * @throws DukeException task list is empty.
     * @return The response.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "task list should not be null.";
        boolean isEmptyList = taskList.size() == 0;
        if (isEmptyList) {
            throw new EmptyTaskListDukeException();
        }
        String result = SUCCESS_MESSAGE + taskList.toString();
        return result;
    }

    /**
     * A boolean to notate if this is an exit command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
