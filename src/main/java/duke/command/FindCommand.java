package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.data.Ui;

/**
 * Command that displays a filtered Tasklist when executed.
 */
public class FindCommand extends Command {
    /** Keyword that the Tasklist will be filtered by. */
    private String keyword;

    /**
     * Constructs FindCommand class.
     *
     * @param keyword String that is used to filter through Tasklist.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays a user's saved tasks containing the keyword.
     *
     * @param tasks The list of tasks that a user has.
     * @param ui The ui that sends a filtered TaskList as a string to the user.
     * @param storage Not used for this command.
     * @return The message produced by ui.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasks(tasks.searchByKeyword(keyword));
    }
}
