package duke.command;

import duke.data.TaskHandler;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Deadline" Command.
 *
 * @author Won Ye Ji
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "DEADLINE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": adds a deadline task to the list";

    /**
     * Constructor for DeadlineCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task information.
     */
    public DeadlineCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    /**
     * Executes the "Deadline" Command.
     *
     * @param cmd Command string to be executed.
     * @return Duke's response to the user.
     * @throws DukeException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws DukeException {
        if (cmd.length() < 10) {
            throw new DukeException(Ui.emptyDescription("deadline"));
        } else {
            String[] split = cmd.split("/by ");
            if (split.length > 1) {
                Deadline deadline = new Deadline(split[0].substring(9), split[1]);
                String toPrint = taskHandler.addDeadline(deadline);
                toPrint = toPrint.concat(taskHandler.printNoOfTasks());
                storage.updateFile(taskHandler.formatTasksToSave());
                return toPrint;
            } else {
                throw new DukeException(Ui.dateMissing());
            }
        }
    }
}
