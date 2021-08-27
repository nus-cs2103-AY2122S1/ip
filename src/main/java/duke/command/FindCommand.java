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

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskHandler taskHandler, Storage storage, Ui ui) {
        taskHandler.filterListByKeyword(keyword);
    }
}