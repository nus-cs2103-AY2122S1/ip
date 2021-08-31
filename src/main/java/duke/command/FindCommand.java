package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The FindCommand class encapsulates all data and instructions
 * needed to find all tasks that contain a keyword.
 */
public class FindCommand extends Command {
    /** The keyword to be matched in the find operation. */
    private String keyword;

    /**
     * Constructs a find command object with the necessary information to execute a find operation.
     *
     * @param keyword The keyword to be matched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the instructions for a find operation.
     *
     * @param taskList The task list currently loaded on Duke.
     * @param ui The object representing the UI of Duke.
     * @param storage The object representing the storage of the Duke program.
     * @return A string to be displayed to the user on the user interface.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList results = taskList.getMatchingTasks(this.keyword);
        return ui.formatMatchingTasks(results);
    }
}
