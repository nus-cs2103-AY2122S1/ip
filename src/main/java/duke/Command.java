package duke;

/**
 * Represents an abstract Command class.
 * 
 * @author Sherman Ng Wei Sheng
 */
public abstract class Command {

    /**
     * Returns true if the command is a programme terminating command.
     *
     * @return True if it is a terminating command and false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Executes the given command.
     * 
     * @param list TaskList before execution of the command.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     * @throws DukeException If problem encountered during execution of command.
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;
}
