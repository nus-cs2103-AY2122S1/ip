package duke.command;

import duke.Task;
import duke.TaskList;
import duke.ui.UserInterface;

/**
 * Represents a command that adds a Task to a TaskList
 */
public class CommandAdd extends Command {
    private Task task;

    /**
     * Class constructor
     * @param task The Task to be added to TaskLists from the execution of this Command
     */
    public CommandAdd(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui) {
        taskList.add(task);
        ui.print("Added task: " + task);
    }
}
