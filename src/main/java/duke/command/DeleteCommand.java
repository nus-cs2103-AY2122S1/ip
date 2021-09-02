package duke.command;

import duke.data.TaskHandler;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * Class that encapsulates the "Delete" Command.
 *
 * @author Won Ye Ji
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "DELETE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Deletes selected task from list.";

    /**
     * Constructor for DeleteCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task information.
     */
    public DeleteCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    /**
     * Executes the "Delete" Command.
     *
     * @param cmd Command string to be executed.
     * @return Duke's response to the user.
     * @throws DukeException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws DukeException {
        Integer index = Integer.parseInt(cmd.substring(7));
        int size = taskHandler.getList().size();
        if (index >= 1 && index <= size) {
            String toPrint = taskHandler.deleteTask(index);
            toPrint = toPrint.concat(taskHandler.printNoOfTasks());
            storage.updateFile(taskHandler.formatTasksToSave());
            return toPrint;
        } else {
            throw new DukeException("Please enter a value from 1 to " + size);
        }
    }
}
