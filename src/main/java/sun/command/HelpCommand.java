package sun.command;

import sun.data.TaskHandler;
import sun.storage.Storage;
import sun.ui.Ui;

/**
 * Class that encapsulates the "Help" Command.
 *
 * @author Won Ye Ji
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "HELP";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": displays this user guide.\n*Format: help\n";

    /**
     * Constructor for HelpCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the tasklist.
     * @param arc Archive that holds the task information.
     */
    public HelpCommand(TaskHandler th, Storage str, Storage arc) {
        super(th, str, arc);
    }

    /**
     * Executes the "Help" Command.
     *
     * @param cmd Command string to be executed.
     * @return Sun's response to the user.
     */
    @Override
    public String execute(String cmd) {
        return Ui.getUserGuide();
    }
}
