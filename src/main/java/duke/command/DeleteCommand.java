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
     * @return string that represents details of deleting this task.
     */
    @Override
    public String execute() {
        int minCommandLength = 6;
        if (input.length() == minCommandLength) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("delete"));
        }
        return super.taskList.removeTask(Integer.parseInt(input.substring(minCommandLength + 1)));
    }
}
