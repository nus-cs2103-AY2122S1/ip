package duke.command;

import duke.data.TaskHandler;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Bye" Command.
 *
 * @author Won Ye Ji
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "BYE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Terminates Duke";

    /**
     * Constructor for ByeCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task information.
     */
    public ByeCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    /**
     * Executes the "Bye" Command.
     *
     * @param cmd Command string to be executed.
     */
    @Override
    public void execute(String cmd) {
        Ui.bye();
        System.exit(0);
    }
}