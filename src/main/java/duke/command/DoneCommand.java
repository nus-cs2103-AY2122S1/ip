package duke.command;

import duke.data.TaskHandler;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * Class that encapsulates the "Done" Command.
 *
 * @author Won Ye Ji
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "DONE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": marks selected task as done in the list";

    /**
     * Constructor for DoneCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task information.
     */
    public DoneCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    /**
     * Executes the "Done" Command.
     *
     * @param cmd Command string to be executed.
     */
    @Override
    public String execute(String cmd) throws DukeException {
        Integer index = Integer.parseInt(cmd.substring(5));
        int size = taskHandler.getList().size();
        if (index >= 1 && index <= size) {
            storage.updateFile(taskHandler.formatTasksToSave());
            return taskHandler.markTaskAsDone(index);
        } else {
            throw new DukeException("Please enter a value from 1 to " + size);
        }
    }
}
