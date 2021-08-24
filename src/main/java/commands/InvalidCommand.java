package commands;
import tasks.TaskList;
import exceptions.DukeException;

/**
 * This class inherits from Command and encapsulates an invalid command.
 * Execution of this command will send a message to user to enter a
 * valid command.
 */
public class InvalidCommand extends Command {
    /**
     * Throws an exception to prompt user to enter a valid command.
     * @param taskList The existing list of tasks.
     * @return The completion message after execution.
     * @throws DukeException
     */
    public String execute(TaskList taskList) throws DukeException {
        throw new DukeException("OOPS!!! Please enter a valid command");
    }
}
