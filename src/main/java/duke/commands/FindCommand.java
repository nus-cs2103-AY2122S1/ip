package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that contains find command
 *
 */
public class FindCommand extends Command {

    /** Input string to be searched for in the tasklist */
    private String searchedString;

    /**
     * Constructor for the find command class
     *
     * @param searchedString Input string to be searched for in the tasklist
     */
    public FindCommand(String searchedString) {
        this.searchedString = searchedString;
    }

    /**
     * Method to execute find command
     *
     * @param taskList The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     */
    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        ui.listFoundTasks(taskList.findTasks(searchedString));
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
