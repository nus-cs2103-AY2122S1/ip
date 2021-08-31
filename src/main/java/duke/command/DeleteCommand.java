package duke.command;

import duke.Duke;
import duke.task.Task;
import duke.task.TaskList;
import duke.GUI;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the general delete command.
 */
public class DeleteCommand extends Command {
    private final int INDEX;

    /** Constructor for the Delete command.
     *
     * @param duke Duke chatbot that is in use.
     * @param index index of the task to be deleted.
     */
    public DeleteCommand(Duke duke, int index) {
        super(duke);
        this.INDEX = index;
    }

    @Override
    public String execute(TaskList taskList) throws IOException {
        Task taskRemoved = this.duke.deleteTask(this.INDEX);
        this.duke.saveTasks();
        return GUI.printDeleteTaskMessage(taskRemoved, taskList.getTotal());
    }
}
