package duke.command;

import duke.data.TaskHandler;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Help" Command.
 *
 * @author Won Ye Ji
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "HELP";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": displays this user guide.";

    /**
     * Constructor for HelpCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task information.
     */
    public HelpCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    /**
     * Executes the "Help" Command.
     *
     * @param cmd Command string to be executed.
     * @return Duke's response to the user.
     */
    @Override
    public String execute(String cmd) {
        return Ui.userGuide();
    }
}
