package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui.Ui;
import java.util.ArrayList;

public class SearchCommand extends Command {

    private String searchString;

    public SearchCommand(String searchString) {
        super(CommandType.SEARCH, false);
        this.searchString = searchString;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task>  listFound = tasks.searchTaskList(this.searchString, tasks);
        ui.showSearchMessage(listFound);
    }


}
