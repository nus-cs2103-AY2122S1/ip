package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

public class SearchCommand extends Command {

    public SearchCommand(String searchTerm) {
        super(searchTerm);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList matchingTasks = taskList.search(super.getExtraInput());
        ui.showSearchResults(matchingTasks);
    }
}
