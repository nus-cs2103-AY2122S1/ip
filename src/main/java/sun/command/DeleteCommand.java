package sun.command;

import sun.data.TaskHandler;
import sun.data.exception.SunException;
import sun.storage.Storage;
import sun.ui.Ui;

/**
 * Class that encapsulates the "Delete" Command.
 *
 * @author Won Ye Ji
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "DELETE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": deletes selected task.\n*Format: delete <index> (e.g delete 1)\n";

    /**
     * Constructor for DeleteCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the tasklist.
     * @param arc Archive that holds the task information.
     */
    public DeleteCommand(TaskHandler th, Storage str, Storage arc) {
        super(th, str, arc);
    }

    /**
     * Executes the "Delete" Command.
     *
     * @param cmd Command string to be executed.
     * @return Sun's response to the user.
     * @throws SunException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws SunException {
        int minCommandLength = 8;
        if (cmd.length() < minCommandLength) {
            throw new SunException(Ui.getMissingIndexError("delete"));
        } else {
            int index;
            int size = taskHandler.getList().size();
            try {
                index = Integer.parseInt(cmd.substring(7));
            } catch (NumberFormatException e) {
                throw new SunException(Ui.getCommandFormatError("delete"));
            }
            if (size == 0) {
                throw new SunException(Ui.getNoTasksError("delete"));
            } else if (index < 1 || index > size) {
                throw new SunException(Ui.getIndexOutOfBoundsError(size));
            } else {
                String toPrint = taskHandler.deleteTask(index);
                toPrint = toPrint.concat(taskHandler.printNoOfTasks());
                storage.updateFile(taskHandler.formatTasksToSave("storage"));
                return toPrint;
            }
        }
    }
}
