package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.UiInterface;

/**
 * Class that handles the List command
 */
public class List extends Command {

    /**
     * Executes the List command.
     *
     * @param taskList Current list of tasks
     * @param ui Ui to interact with user
     * @param storage Storage that allows loading/saving
     * @throws DukeException if an error is encountered
     */
    @Override
    public void execute(TaskList taskList, UiInterface ui, Storage storage) {
        ui.print(taskList.stringifyTasksForList());
    }

    /**
     * Returns if the command is an exit.
     *
     * @return boolean indicating if command is exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
