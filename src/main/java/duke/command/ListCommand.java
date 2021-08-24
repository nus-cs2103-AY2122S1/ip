package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class used to print all tasks.
 */
public class ListCommand implements Command {

    /**
     *
     * @param taskList Manages all current tasks
     * @param ui Used to print messages
     * @param storage Loads and saves the tasks to a txt file
     * @throws DukeException thrown if there are input/parsing errors
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printAll(taskList.getTasks(), "Here are the tasks in your list:");
    }

    /**
     * Method to determine if Duke should stop running.
     *
     * @return true as this is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}