package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to search for tasks containing the keyword.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-9
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param taskList Handles all operations regarding tasks.
     * @param storage  Save and load data from local directory.
     * @param ui       Prints message with respect to user input.
     * @param keyword  Keyword to find related tasks.
     */
    public FindCommand(TaskList taskList, Storage storage, Ui ui, String keyword) {
        super(taskList, storage, ui);
        this.keyword = keyword;
    }

    /**
     * Executes command to print out
     * every task containing the keyword.
     */
    @Override
    public void runCommand() {
        ui.printFindTask(taskList.findRelatedTask(keyword));
    }
}
