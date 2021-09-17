package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Done" Command.
 *
 * @author Wang Hong Yong
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "DONE";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + " : Marks selected Task as done.";
    public static final String COMMAND_FORMAT = "Format: done <Task Number> "
            + "(e.g done 1)\n";
    private String input;

    /**
     * Constructor for Done Command.
     *
     * @param tasklist Task Handler that handles the operation.
     * @param input command string to be executed.
     */
    public DoneCommand(TaskList tasklist, String input) {
        super(tasklist);
        this.input = input;
    }

    /**
     * Executes the "Done" Command.
     *
     * @return string that represents details of completing this task.
     * @throws DukeException if task faces an error during execution.
     */
    @Override
    public String execute() throws DukeException {
        int minCommandLength = 6;
        if (input.length() < minCommandLength) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("done"));
        }
        return super.taskList.markTaskAsDone(input, minCommandLength);
    }
}
