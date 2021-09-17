package sun.command;

import sun.data.TaskHandler;
import sun.data.exception.SunException;
import sun.storage.Storage;
import sun.ui.Ui;

/**
 * Class that encapsulates the "Archive" Command.
 *
 * @author Won Ye Ji
 */
public class ArchiveCommand extends Command {
    public static final String COMMAND_WORD = "ARCHIVE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": archives selected task.\n*Format: archive <index> (e.g archive 1)\n";

    /**
     * Constructor for ArchiveCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the tasklist.
     * @param arc Archive that holds the task information.
     */
    public ArchiveCommand(TaskHandler th, Storage str, Storage arc) {
        super(th, str, arc);
    }

    /**
     * Executes the "Archive" Command.
     *
     * @param cmd Command string to be executed.
     * @return Sun's response to the user.
     * @throws SunException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws SunException {
        int minCommandLength = 9;
        if (cmd.length() < minCommandLength) {
            throw new SunException(Ui.getMissingIndexError("archive"));
        } else {
            int index;
            int size = taskHandler.getList().size();
            try {
                index = Integer.parseInt(cmd.substring(8));
            } catch (NumberFormatException e) {
                throw new SunException(Ui.getCommandFormatError("archive"));
            }
            if (size == 0) {
                throw new SunException(Ui.getNoTasksError("archive"));
            } else if (index < 1 || index > size) {
                throw new SunException(Ui.getIndexOutOfBoundsError(size));
            } else {
                String toPrint = taskHandler.archiveTask(index);
                toPrint = toPrint.concat(taskHandler.printNoOfTasks());
                archives.updateFile(taskHandler.formatTasksToSave("archives"));
                storage.updateFile(taskHandler.formatTasksToSave("storage"));
                return toPrint;
            }
        }
    }
}
