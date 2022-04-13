package duke.command;

import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Command that displays tasks in the TaskList.
 */
public class DisplayCommand extends Command {

    /**
     * Executes the display list command.
     *
     * @param tasks   The task list to execute the command on.
     * @param storage The storage for the tasks.
     * @return a string output.
     */
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.getListSize() == 0) {
            return "No tasks in your list.";
        }

        return formatOutput("Your task list:", formatOutput(tasks.getTaskStrings()));
    }

    /**
     * Returns false to continue the program.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}
