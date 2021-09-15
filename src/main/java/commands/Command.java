package commands;
import exceptions.MorganException;
import storage.Storage;
import tasks.TaskList;

/**
 * This is an abstract Command class. Sub-classes of Command class will have
 * to inherit the execute() method to simulate the execution of the command.
 */
public abstract class Command {
    /**
     * An abstract execute method to be inherited by sub-classes of Command.
     * @param tasks The existing list where the task.
     * @param storage The storage object of morgan.
     * @return The completion message after execution.
     * @throws MorganException If input is invalid.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws MorganException;
}
