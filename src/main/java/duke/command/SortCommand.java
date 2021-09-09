package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a Sort command that deals sorting the task in the task list.
 *
 * @author Zhi Bin
 * @version Duke Level 10
 */
public class SortCommand extends DukeCommand{
    /**
     * Constructor for a DukeCommand.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     */
    public SortCommand(Ui ui, Storage storage, TaskList list) {
        super(ui, storage, list);
    }

    /**
     * Executes the command.
     *
     * @return A message to be displayed on the GUI.
     * @throws DukeException When an error occurred.
     */
    @Override
    public String execute() throws DukeException {
        list.sortTaskList();
        return ui.sortListMessage(list.getList());
    }
}
