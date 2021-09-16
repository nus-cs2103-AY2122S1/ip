package sun.command;

import sun.data.TaskHandler;
import sun.data.exception.SunException;
import sun.storage.Storage;
import sun.ui.Ui;

/**
 * Class that encapsulates the "Done" Command.
 *
 * @author Won Ye Ji
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "DONE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": marks selected task as done.\n*Format: done <index> (e.g done 1)\n";

    /**
     * Constructor for DoneCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the tasklist.
     * @param arc Archive that holds the task information.
     */
    public DoneCommand(TaskHandler th, Storage str, Storage arc) {
        super(th, str, arc);
    }

    /**
     * Executes the "Done" Command.
     *
     * @param cmd Command string to be executed.
     * @return Sun's response to the user.
     * @throws SunException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws SunException {
        int minCommandLength = 6;
        if (cmd.length() < minCommandLength) {
            throw new SunException(Ui.getMissingIndexError("done"));
        } else {
            int index;
            int size = taskHandler.getList().size();
            try {
                index = Integer.parseInt(cmd.substring(5));
            } catch (NumberFormatException e) {
                throw new SunException(Ui.getCommandFormatError("done"));
            }
            if (size == 0) {
                throw new SunException(Ui.getNoTasksError("done"));
            } else if (index < 1 || index > size) {
                throw new SunException(Ui.getIndexOutOfBoundsError(size));
            } else if (taskHandler.getList().get(index - 1).getStatusIcon() == "X") {
                throw new SunException(Ui.getAlreadyMarkedAsDoneError());
            } else {
                String toPrint = taskHandler.markTaskAsDone(index);
                storage.updateFile(taskHandler.formatTasksToSave("storage"));
                return toPrint;
            }
        }
    }
}
