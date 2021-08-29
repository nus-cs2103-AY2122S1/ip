package commands;
import exceptions.MorganException;
import tasks.TaskList;

/**
 * This is an abstract Command class. Sub-classes of Command class will have
 * to inherit the execute() method to simulate the execution of the command.
 */
public abstract class Command {
    /**
     * An abstract execute method to be inherited by sub-classes of Command.
     * @param taskList The existing list where the task.
     * @return The completion message after execution.
     * @throws MorganException
     */
    public abstract String execute(TaskList taskList) throws MorganException;
}
