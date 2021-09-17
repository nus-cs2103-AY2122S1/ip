package sun.command;

import sun.data.TaskHandler;
import sun.data.exception.SunException;
import sun.data.task.Event;
import sun.storage.Storage;
import sun.ui.Ui;

/**
 * Class that encapsulates the "Event" Command.
 *
 * @author Won Ye Ji
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "EVENT";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": adds an event task.\n*Format: event <description> /at ddMMyy HHmm "
            + "(e.g event party /at 120421 1700)\n";

    /**
     * Constructor for EventCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the tasklist.
     * @param arc Archive that holds the task information.
     */
    public EventCommand(TaskHandler th, Storage str, Storage arc) {
        super(th, str, arc);
    }

    /**
     * Executes the "Event" Command.
     *
     * @param cmd Command string to be executed.
     * @return Sun's response to the user.
     * @throws SunException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws SunException {
        int minCommandLength = 7;
        if (cmd.length() < minCommandLength) {
            throw new SunException(Ui.getCommandFormatError("event"));
        } else {
            String[] split = cmd.split("/at ");
            if (split.length == 1 && !split[0].equals(cmd)) {
                throw new SunException(Ui.getDateMissingError());
            } else if (split[0].equals(cmd)) {
                throw new SunException(Ui.getCommandFormatError("event"));
            } else {
                Event event = new Event(split[0].substring(6), split[1]);
                if (event.formatDate(split[1]) == "Format error") {
                    throw new SunException(Ui.getDateFormatError());
                } else {
                    String toPrint = taskHandler.addTask(event);
                    toPrint = toPrint.concat(taskHandler.printNoOfTasks());
                    storage.updateFile(taskHandler.formatTasksToSave("storage"));
                    return toPrint;
                }
            }
        }
    }
}
