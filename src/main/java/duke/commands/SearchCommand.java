package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;

/**
 * The SearchCommand class extends the Command class and is the Command that searches
 * for a Task in the TaskList.
 */
public class SearchCommand extends Command {

    /** The String to be searched for in the Tasks in the TaskList. */
    private String searchString;

    /**
     * The constructor for the SearchCommand object.
     *
     * @param searchString The String being searched for
     */
    public SearchCommand(String searchString) {
        super(CommandType.SEARCH, false);
        this.searchString = searchString;
    }

    /**
     * The execute command that executes the necessary actions when issued with the Search Command.
     *
     * @param tasks The TaskList to be added to
     * @param ui The Ui object to interact with the user
     * @param storage The Storage object that stores the TaskList on the Local Machine
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task>  listFound = tasks.searchTaskList(this.searchString, tasks);
        ui.showSearchMessage(listFound);
    }


}
