package duke.command;

import duke.io.ResponseManager;
import duke.Storage;
import duke.task.TaskManager;

/**
 * This class encapsulates the command to display the current tasks.
 * It is triggered by the parser and uses the TaskManager and Ui.
 */
public class ListTasksCommand implements ICommand {

    private String reply;

    /**
     * Calls the Ui object to display the current list of tasks.
     *
     * @param tm The TaskManager object controlling the tasks in Duke.
     * @param responseManager The Ui object managing Duke's user interface.
     * @param storage The Storage object managing the local storing of tasks.
     */
    public void execute(TaskManager tm, ResponseManager responseManager, Storage storage) {
        reply = responseManager.getListTasksMessage(tm.getTasks());
    }

    public String getReply() {
        return reply;
    }

}
