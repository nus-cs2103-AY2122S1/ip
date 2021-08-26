package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * FindCommand Class for the find commands
 */
public class FindCommand extends Command {
    private final String string;

    /**
     * FindCommand Constructor
     *
     * @param string is the string to be searched
     */
    public FindCommand(String string) throws DukeException {
        super(true);
        if (string == null) {
            throw new DukeException("☹ OOPS!!! find will require a pattern to find.");
        } else {
            this.string = string;
        }
    }

    /**
     * Executes the Search Command to print the list of task with the given string to the user via the Ui
     *
     * @param taskList The current list of tasks
     * @param ui       The current Ui
     * @param storage  The current storage class to handle the txt file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.searchList(taskList, this.string);
    }
}
