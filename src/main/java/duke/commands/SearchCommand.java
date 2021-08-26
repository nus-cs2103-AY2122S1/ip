package duke.commands;

import duke.storage.Storage;
import duke.commands.Command;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
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
