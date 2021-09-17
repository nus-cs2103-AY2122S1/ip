package sun.command;

import sun.data.TaskHandler;
import sun.storage.Storage;

/**
 * Class that encapsulates the "Archives" Command.
 *
 * @author Won Ye Ji
 */
public class ArchivesCommand extends Command {
    public static final String COMMAND_WORD = "ARCHIVES";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": displays all archived tasks.\n*Format: archives\n";

    /**
     * Constructor for ArchivesCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the tasklist.
     * @param arc Archive that holds the task information.
     */
    public ArchivesCommand(TaskHandler th, Storage str, Storage arc) {
        super(th, str, arc);
    }

    /**
     * Executes the "Archives" Command.
     *
     * @param cmd Command string to be executed.
     * @return Sun's response to the user.
     */
    @Override
    public String execute(String cmd) {
        return taskHandler.printList("archives");
    }
}
