package duke.command;

import duke.DukeException;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Command that displays tasks in the TaskList.
 */
public class HelpWindowCommand extends Command {

    /**
     * Executes the help window command.
     *
     * @param tasks   The task list to execute the command on.
     * @param storage The storage for the tasks.
     * @return a string output.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return ":help";
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
