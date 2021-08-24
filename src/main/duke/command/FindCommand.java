package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFilteredList(tasks, keyword);
    }
}
