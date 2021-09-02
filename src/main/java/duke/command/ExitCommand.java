package duke.command;

import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Command that exits the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the program exit command.
     *
     * @param tasks   The task list to execute the command on.
     * @param storage The storage for the tasks.
     * @return a string output.
     */
    public String execute(TaskList tasks, Storage storage) {
        return "Bye bye! See you again soon!";
    }

    /**
     * Returns true to exit the program.
     *
     * @return true.
     */
    public boolean isExit() {
        return true;
    }
}
