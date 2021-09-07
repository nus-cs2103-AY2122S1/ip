package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The ListCommand is a Command that facilitates the display of the task on the ui
 *
 */
public class ListCommand extends Command {

    /**
     * Calls the displayList method in the ui which prints out the list
     *
     * @param taskList the list of tasks upon which the operations need to be performed
     * @param ui the ui in which a message relating to the command execution is displayed to the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.displayList(taskList);
    }
}
