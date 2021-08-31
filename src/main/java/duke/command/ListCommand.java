package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * The ListCommand class represents the list command for displaying the list of Task in TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command which displays all the currently added Task in TaskList.
     * @param taskList
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @return String The String representation of the full list of Task.
     * @throws DukeException Unused.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.displayListOfTasks(taskList);
    }
}
