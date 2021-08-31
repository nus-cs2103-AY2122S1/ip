package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.displayFoundTasks(taskList.findMatchingTasks(query));
    }
}
