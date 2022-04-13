package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The FindCommand handles the command to find tasks associated with the given keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand object with a given keyword.
     *
     * @param keyword The keyword to find tasks with.
     * @throws DukeException If no keyword entered.
     */
    public FindCommand(String keyword) throws DukeException {
        if (keyword.isBlank()) {
            throw new DukeException("Keyword can't be empty.");
        }
        this.keyword = keyword;
    }

    /**
     * Executes the finding of all tasks associated with the given keyword.
     *
     * @param taskList The current TaskList being used.
     * @param ui The current Ui being used.
     * @param storage The current Storage being used.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.showMatchingTasks(taskList.findMatchingTasks(keyword));
    }

}
