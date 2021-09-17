package sun.command;

import sun.data.TaskHandler;
import sun.data.exception.SunException;
import sun.storage.Storage;
import sun.ui.Ui;

/**
 * Class that encapsulates the "Find" Command.
 *
 * @author Won Ye Ji
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "FIND";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": finds tasks with matching keyword.\n*Format: find <keyword> (e.g find book)\n";

    /**
     * Constructor for FindCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the tasklist.
     * @param arc Archive that holds the task information.
     */
    public FindCommand(TaskHandler th, Storage str, Storage arc) {
        super(th, str, arc);
    }

    /**
     * Executes the "Find" Command.
     *
     * @param cmd Command string to be executed.
     * @return Sun's response to the user.
     * @throws SunException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws SunException {
        int minCommandLength = 6;
        if (cmd.length() < minCommandLength) {
            throw new SunException(Ui.getCommandFormatError("find"));
        } else {
            String keyword = cmd.substring(6);
            return taskHandler.findTasks(keyword);
        }
    }
}

