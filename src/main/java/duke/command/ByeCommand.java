package duke.command;

import duke.io.ResponseManager;
import duke.Storage;
import duke.task.TaskManager;

/**
 * This class encapsulates the command to cease the conversation with Duke.
 * It is triggered by the parser and uses the Ui.
 */
public class ByeCommand implements ICommand {

    private String reply;

    /**
     * Calls the Ui object to display the 'bye' message then ends the program.
     *
     * @param tm The TaskManager object controlling the tasks in Duke.
     * @param responseManager The Ui object managing Duke's user interface.
     * @param storage The Storage object managing the local storing of tasks.
     */
    public void execute(TaskManager tm, ResponseManager responseManager, Storage storage) {
        reply = responseManager.getByeMessage();
        System.exit(0);
    }

    public String getReply() {
        return getReply();
    }

}
