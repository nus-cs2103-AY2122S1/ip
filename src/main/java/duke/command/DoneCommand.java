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
     * Constructor for DeleteCommand.
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
     */
    public String execute() {
        if (input.length() == 4) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("done"));
        }
        return taskList.markTaskAsDone(Integer.parseInt(input.substring(5)));
    }
}
