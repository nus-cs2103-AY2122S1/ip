package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.GUI;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents the general task command.
 */
public class TaskCommand extends Command {
    private final String DESCRIPTION;

    /**
     * Constructor for the Task command.
     * @param duke Duke chatbot that is in use.
     * @param description full command in String inputted by the user.
     */
    public TaskCommand(Duke duke, String description) {
        super(duke);
        this.DESCRIPTION = description;
    }

    @Override
    public String execute(TaskList taskList) throws IOException, DukeException {
        Task taskAdded = this.duke.addTaskToList(DESCRIPTION);
        this.duke.saveTasks();
        return GUI.sendAddedTaskMessage(taskAdded, taskList.getTotal());
    }
}
