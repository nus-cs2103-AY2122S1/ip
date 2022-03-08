package duke.command;

import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

public class SortCommand extends Command {
    private final boolean isReverse;

    /**
     * Constructor for SortCommand.
     *
     * @param isReverse Indicates whether task list is sorted in reverse.
     */
    public SortCommand(boolean isReverse) {
        this.isReverse = isReverse;
    }

    /**
     * Sorts the filtered task list.
     *
     * @param taskHandler TaskHandler for Duke.
     * @param storage Storage for Duke.
     * @param ui The user interface.
     * @return Message to be shown to user.
     */
    @Override
    public String execute(TaskHandler taskHandler, Storage storage, Ui ui) {
        return taskHandler.sortTaskList(isReverse);
    }
}