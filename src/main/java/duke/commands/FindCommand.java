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
     * Executes find command
     *
     * @param taskList The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     * @return Duke's String response
     */
    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        return ui.listFoundTasks(taskList.findTasks(searchedString));
    }
}
