package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Gui;
import duke.task.TaskList;

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
     * @param gui The user interface to display the reply.
     * @param storage The place to store the session.
     * @throws DukeException task list is empty.
     */

    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("It seems that your task list is empty.\n"
                    + "Try adding some task using \"todo\", \"deadline\" or \"event\"");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here is your task list:\n");
        sb.append(taskList.toString());
        gui.showResponse(sb.toString());
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
