package duke.command;

import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

/**
 * This class encapsulates the find command.
 *
 * @author Teo Sin Yee
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Instantiates a new FindCommand.
     *
     * @param keyword Keyword to filter the list with.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Generates the filtered task list.
     *
     * @param taskHandler TaskHandler for Duke.
     * @param storage Storage for Duke.
     * @param ui The user interface.
     * @return Filtered task list.
     */
    @Override
    public String execute(TaskHandler taskHandler, Storage storage, Ui ui) {
        return taskHandler.generateFilteredList(keyword);
    }
}
