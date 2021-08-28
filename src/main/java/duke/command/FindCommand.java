package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * FindCommand is a duke.command which prints out the Tasks in the TaskList which
 * contains the keyword provided.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class FindCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it is either empty, or contains a date for which tasks with that date should be returned.
     */
    public FindCommand(String description) {
        super(description);
    }

    /**
     * Lists down all the tasks (of the specified date).
     *
     * @param tasks   the duke.task list
     * @param ui      the ui
     * @param storage the storage for the saved duke.task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String searchString = super.description.trim();
        if (searchString.equals("")) {
            tasks.printList();
        } else {
            tasks.printListSearch(searchString);
        }
    }
}
