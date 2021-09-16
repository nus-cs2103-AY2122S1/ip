package duke.command;

import duke.misc.TaskList;
import duke.misc.Ui;

/**
 * ListCommand class to represent the command to list the current tasks.
 */
public class ListCommand extends Command {
    private final boolean isBye;

    /**
     * Constructor for ListCommand class.
     */
    public ListCommand() {
        isBye = false;
    }

    /**
     * Executes the operation to list the current tasks.
     *
     * @param tl The current TaskList.
     * @return String to notify user of successful command execution.
     */
    public String execute(TaskList tl) {
        return Ui.LIST_MSG + tl.displayList();
    }

    public boolean getIsBye() {
        return isBye;
    }
}
