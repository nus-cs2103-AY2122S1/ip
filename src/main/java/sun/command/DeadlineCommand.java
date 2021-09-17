package sun.command;

import sun.data.TaskHandler;
import sun.data.exception.SunException;
import sun.data.task.Deadline;
import sun.storage.Storage;
import sun.ui.Ui;

/**
 * Class that encapsulates the "Deadline" Command.
 *
 * @author Won Ye Ji
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "DEADLINE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": adds a deadline task.\n*Format: deadline <description> /by ddMMyy HHmm "
            + "(e.g deadline essay /by 120421 2359)\n";

    /**
     * Constructor for DeadlineCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the tasklist.
     * @param arc Archive that holds the task information.
     */
    public DeadlineCommand(TaskHandler th, Storage str, Storage arc) {
        super(th, str, arc);
    }

    /**
     * Executes the "Deadline" Command.
     *
     * @param cmd Command string to be executed.
     * @return Sun's response to the user.
     * @throws SunException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws SunException {
        int minCommandLength = 10;
        if (cmd.length() < minCommandLength) {
            throw new SunException(Ui.getCommandFormatError("deadline"));
        } else {
            String[] split = cmd.split("/by ");
            if (split.length == 1 && !split[0].equals(cmd)) {
                throw new SunException(Ui.getDateMissingError());
            } else if (split[0].equals(cmd)) {
                throw new SunException(Ui.getCommandFormatError("deadline"));
            } else {
                Deadline deadline = new Deadline(split[0].substring(9), split[1]);
                if (deadline.formatDate(split[1]) == "Format error") {
                    throw new SunException(Ui.getDateFormatError());
                } else {
                    String toPrint = taskHandler.addTask(deadline);
                    toPrint = toPrint.concat(taskHandler.printNoOfTasks());
                    storage.updateFile(taskHandler.formatTasksToSave("storage"));
                    return toPrint;
                }
            }
        }
    }
}
