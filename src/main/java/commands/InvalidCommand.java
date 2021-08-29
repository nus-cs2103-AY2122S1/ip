package commands;
import exceptions.MorganException;
import tasks.TaskList;

/**
 * This class inherits from Command and encapsulates an invalid command.
 * Execution of this command will send a message to user to enter a
 * valid command.
 */
public class InvalidCommand extends Command {
    private final String INVALID_COMMAND_ERROR_MESSAGE = "Please enter a valid command.";

    /**
     * Throws an exception to prompt user to enter a valid command.
     * @param taskList The existing list of tasks.
     * @return The completion message after execution.
     * @throws MorganException
     */
    public String execute(TaskList taskList) throws MorganException {
        throw new MorganException(INVALID_COMMAND_ERROR_MESSAGE);
    }
}
