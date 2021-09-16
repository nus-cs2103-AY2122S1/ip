package nyx.command;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.Deadline;
import nyx.task.TaskList;

/**
 * Represents a command to add a DeadLine task.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructs a DeadlineCommand object.
     *
     * @param information Information needed to run the command.
     */
    public DeadlineCommand(String information) {
        super(information);
    }

    /**
     * Perform operations needed to add a DeadLine task.
     *
     * @param taskList TaskList object containing all the tasks.
     * @param storage Storage object to deal with hard disk related operations.
     * @return String representation of the message for the user.
     * @throws NyxException If an error is encountered when adding the DeadLine task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws NyxException {
        String[] splitInfo = information.split(" /by ");
        if (splitInfo.length != 2) {
            throw new NyxException("Incorrect Deadline format! The correct format is { details } /by { datetime }");
        }
        Deadline deadline = new Deadline(splitInfo[0].strip(), splitInfo[1]);
        return AddHandler.handleAdd(deadline, taskList, storage);
    }

    public static void throwEmptyException() throws NyxException {
        throw new NyxException("The description of a deadline cannot be empty.");
    }
}
