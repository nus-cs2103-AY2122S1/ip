package duke.command;

import java.util.ArrayList;

import duke.Action;
import duke.Storage;
import duke.StringUtils;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
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
    public void executeAndShow(TaskList taskList, Storage storage) {
        Ui.showMultiLines(execute(taskList, storage));
    }

    /**
     * Returns the result of executing the find command.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     * @return A string representation of the result.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert super.getAction() == Action.FIND : "Find command action type error";
        ArrayList<Task> matchTasks = taskList.findTask(query);
        return StringUtils.getSearchMessage(matchTasks);
    }
}
