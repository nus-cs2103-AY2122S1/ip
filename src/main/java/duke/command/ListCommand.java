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
     * Method which calls for the Ui to display the list of Task.
     *
     * @param taskList
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @throws DukeException Unused.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.displayListOfTasks(taskList);
    }
}
