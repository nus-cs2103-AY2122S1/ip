package duke.command;

import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

public class SortCommand extends Command {
    private final boolean isReverse;

    /**
     *
     * @param isReverse
     */
    public SortCommand(boolean isReverse) {
        this.isReverse = isReverse;
    }

    @Override
    public String execute(TaskHandler taskHandler, Storage storage, Ui ui) {
        return taskHandler.sortTaskList(isReverse);
    }
}
