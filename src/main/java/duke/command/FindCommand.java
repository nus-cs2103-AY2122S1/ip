package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * FindCommand  find a task by searching for a keyword.
 *
 * @author Chng Zi Hao
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword The keyword to be use for searching.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds task using the keyword.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage Storage for Duke.
     * @retunr Message to be shown to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String message = taskList.filterByKeyword(keyword);
        ui.formatPrint(message);
        return message;
    }
}
