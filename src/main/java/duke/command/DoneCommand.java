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
     * @return Duke's response to the user.
     * @throws DukeException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws DukeException {
        int minCommandLength = 6;
        if (cmd.length() < minCommandLength) {
            throw new DukeException("Please choose a task to mark as done!");
        } else {
            Integer index = Integer.parseInt(cmd.substring(5));
            int size = taskHandler.getList().size();
            if (index < 1 || index > size) {
                throw new DukeException("Please enter a value from 1 to " + size);
            } else if (taskHandler.getList().get(index - 1).getStatusIcon() == "X") {
                throw new DukeException("Task has already been marked as completed!");
            } else {
                String toPrint = taskHandler.markTaskAsDone(index);
                storage.updateFile(taskHandler.formatTasksToSave());
                return toPrint;
            }
        }
    }
}
