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
     * @return string that represents details of completing this task.
     */
    @Override
    public String execute() throws DukeException {
        int minCommandLength = 4;
        if (input.length() == minCommandLength) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("done"));
        }
        return taskList.markTaskAsDone(Integer.parseInt(input.substring(minCommandLength + 1)));
    }
}
