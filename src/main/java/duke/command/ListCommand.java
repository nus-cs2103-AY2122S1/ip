package duke.command;

import duke.data.TaskHandler;
import duke.storage.Storage;

/**
 * Class that encapsulates the "List" Command.
 *
 * @author Won Ye Ji
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "LIST";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers.";

    /**
     * Constructor for ListCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task information.
     */
    public ListCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    /**
     * Executes the "List" Command.
     *
     * @param cmd Command string to be executed.
     */
    @Override
    public void execute(String cmd) {
        taskHandler.printList();
    }
}
