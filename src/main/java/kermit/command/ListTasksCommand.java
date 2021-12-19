package kermit.command;

import kermit.Response;
import kermit.Storage;
import kermit.TaskList;

/**
 * Lists command displays tasks in task list.
 */
public class ListTasksCommand extends Command {
    /**
     * Constructs ListTasks command.
     */
    public ListTasksCommand() {}

    /**
     * Executes exit command.
     * List tasks including their short form, completion status and date associated with task
     * @param taskList Instance of task list used.
     * @param response       Instance of Ui used.
     * @param storage  Instance of storage class used.
     */
    @Override
    public String execute(TaskList taskList, Response response, Storage storage) {
        return response.getListItems(taskList);
    }

    /**
     * Returns if command is the exit command.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns syntax for list command.
     *
     * @return Syntax for how list command is used.
     */
    protected static String getSyntax() {
        return "list";
    }
}
