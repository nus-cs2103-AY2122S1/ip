package duke.commands;

import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that contains the list command
 *
 */
public class ListCommand extends Command {

    /**
     * Constructor for the list command class
     *
     */
    public ListCommand(){};

    /**
     * Method that executes the list command
     *
     * @param taskList The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     */
    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) {
        ui.listTasks(taskList);
    }

    /**
     * Method to return boolean depending on if Duke is to be exited
     *
     * @return boolean that returns false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}