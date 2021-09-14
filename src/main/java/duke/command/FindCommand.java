package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.exception.DukeException;

/**
 * A FindCommand class that extends from the Command class.
 * @author KelvinSoo
 * @version Level-9
 */
public class FindCommand extends Command{

    private final String parameter;

    /**
     * A constructor to initialize a find command.
     * @param parameter the parameter of the find command, keyword for searching task.
     */
    public FindCommand(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Execute a find command.
     * find matching tasks from the task list.
     * @param taskList The task list to search.
     * @param storage The place to store the session.
     * @throws DukeException no matching task found.
     * @return The response.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "task list should not be null.";
        TaskList searchList = new TaskList();

        for (Task t:taskList.getTaskList()) {
            boolean isInList = t.getDescription().contains(parameter);
            if (isInList) {
                searchList.addTask(t);
            }
        }

        boolean isEmptySearchList = searchList.size() == 0;
        if (isEmptySearchList) {
            throw new DukeException("No matching tasks found.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        sb.append(searchList.toString());
        return sb.toString();
    }

    /**
     * A boolean to notate if this is an exit command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
