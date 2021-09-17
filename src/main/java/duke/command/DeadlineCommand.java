package duke.command;


import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Deadline" Command.
 *
 * @author Wang Hong Yong
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "DEADLINE";
    public static final String COMMAND_USAGE = COMMAND_WORD
        + " : adds a deadline task.";
    public static final String COMMAND_FORMAT = "Format: deadline <description> /by <YYYY-MM-DD> <HHmm> "
            + "(e.g deadline essay /by 2019-10-15 2359)\n";
    private String input;


    /**
     * Constructor for DeadlineCommand.
     *
     * @param tasklist Task Handler that handles the operation.
     * @param input command string to be executed.
     */
    public DeadlineCommand(TaskList tasklist, String input) {
        super(tasklist);
        this.input = input;
    }

    /**
     * Executes the "Deadline" Command.
     *
     * @return string that represents details of adding this deadline task.
     * @throws DukeException if task faces an error during execution.
     */
    @Override
    public String execute() throws DukeException {
        int minCommandLength = 10;
        if (input.length() < minCommandLength) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("deadline"));
        }
        try {
            String[] infoArray = input.substring(minCommandLength + 1).split("/by ", 2);
            Deadline d = new Deadline(infoArray[0], infoArray[1], new String[0]);
            return super.taskList.addTask(d);
        } catch (Exception e) {
            throw new DukeException(Ui.getDeadlineFormatMsg());
        }

    }

}
