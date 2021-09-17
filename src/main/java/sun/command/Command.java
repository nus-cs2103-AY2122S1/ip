package sun.command;

import sun.data.TaskHandler;
import sun.data.exception.SunException;
import sun.storage.Storage;

/**
 * Class that represents an executable command.
 *
 * @author Won Ye Ji
 */
public abstract class Command {
    protected TaskHandler taskHandler;
    protected Storage storage;
    protected Storage archives;

    /**
     * Constructor for Command.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task list.
     * @param arc Archive that holds the task information.
     */
    public Command(TaskHandler th, Storage str, Storage arc) {
        this.taskHandler = th;
        this.storage = str;
        this.archives = arc;
    }

    /**
     * Executes the Command.
     *
     * @param cmd Command string to be executed.
     * @return Sun's response to the user.
     * @throws SunException if task faces an error during execution.
     */
    public abstract String execute(String cmd) throws SunException;
}
