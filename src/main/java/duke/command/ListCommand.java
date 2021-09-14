package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.exception.DukeException;

/**
 * A ListCommand class that extends from the Command class.
 *
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class ListCommand extends Command{

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
            throw new DukeException("It seems that your task list is empty.\n"
                    + "Try adding some task using \"todo\", \"deadline\" or \"event\"");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here is your task list:\n");
        sb.append(taskList.toString());
        return sb.toString();
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
