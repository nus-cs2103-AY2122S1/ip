package sun.command;

import sun.data.TaskHandler;
import sun.storage.Storage;

/**
 * Class that encapsulates the "List" Command.
 *
 * @author Won Ye Ji
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "LIST";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": displays all tasks.\n*Format: list\n";

    /**
     * Constructor for ListCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the tasklist.
     * @param arc Archive that holds the task information.
     */
    public ListCommand(TaskHandler th, Storage str, Storage arc) {
        super(th, str, arc);
    }

    /**
     * Executes the "List" Command.
     *
     * @param cmd Command string to be executed.
     * @return Sun's response to the user.
     */
    @Override
    public String execute(String cmd) {
        return taskHandler.printList("storage");
    }
}
