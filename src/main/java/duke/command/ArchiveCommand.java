package duke.command;

import duke.data.TaskHandler;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * Class that encapsulates the "Archive" Command.
 *
 * @author Won Ye Ji
 */
public class ArchiveCommand extends Command {
    public static final String COMMAND_WORD = "ARCHIVE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": archives selected task from list.";

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
     * @return Duke's response to the user.
     * @throws DukeException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws DukeException {
        int minCommandLength = 9;
        if (cmd.length() < minCommandLength) {
            throw new DukeException("Please choose a task to archive!");
        } else {
            Integer index = Integer.parseInt(cmd.substring(8));
            int size = taskHandler.getList().size();
            if (index < 1 || index > size) {
                throw new DukeException("Please enter a value from 1 to " + size);
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
