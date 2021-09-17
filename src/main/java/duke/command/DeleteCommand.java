package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Delete" Command.
 *
 * @author Wang Hong Yong
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "DELETE";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + " : deletes an existing task.";
    public static final String COMMAND_FORMAT = "Format: delete <Task Number> "
            + "(e.g delete 1)\n";
    private String input;

    /**
     * Constructor for DeleteCommand.
     *
     * @param tasklist Task Handler that handles the operation.
     * @param input command string to be executed.
     */
    public DeleteCommand(TaskList tasklist, String input) {
        super(tasklist);
        this.input = input;
    }

    /**
     * Executes the "Delete" Command.
     *
     * @return string that represents details of deleting this task.
     * @throws DukeException if task faces an error during execution.
     */
    @Override
    public String execute() throws DukeException {
        int minCommandLength = 8;
        if (input.length() < minCommandLength) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("delete"));
        }
        return super.taskList.removeTask(input, minCommandLength);
    }
}
