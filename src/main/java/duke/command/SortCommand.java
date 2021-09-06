package duke.command;

import duke.Duke;
import duke.task.Task;
import duke.task.TaskList;
import duke.GUI;

import java.io.IOException;

/**
 * Represents the general delete command.
 */
public class SortCommand extends Command {

    /** Constructor for the Delete command.
     *
     * @param duke Duke chatbot that is in use.
     */
    public SortCommand(Duke duke) {
        super(duke);
    }

    @Override
    public String execute(TaskList taskList) {
        this.duke.sortTasks();
        return GUI.sendSortedMessage(taskList);
    }
}
