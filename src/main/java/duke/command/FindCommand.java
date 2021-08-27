package duke.command;

import duke.Action;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command{
    /** The query from the user */
    private String query;

    /**
     * Constructs a find command using the given action and query.
     *
     * @param action The given action.
     * @param query The given query.
     */
    public FindCommand(Action action, String query) {
        super(action);
        this.query = query;
    }

    /**
     * Finds all the tasks match the query.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        ArrayList<Task> matchTasks = taskList.findTask(query);
        Ui.showFindTasks(matchTasks);
    }
}
