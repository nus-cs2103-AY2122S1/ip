package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Todo;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Todo" Command.
 *
 * @author Wang Hong Yong
 */
public class TodoCommand extends Command {
    private String input;

    /**
     * Constructor for TodoCommand.
     *
     * @param tasklist Task Handler that handles the operation.
     * @param input command string to be executed.
     */
    public TodoCommand(TaskList tasklist, String input) {
        super(tasklist);
        this.input = input;
    }

    /**
     * Executes the "Todo" Command.
     * @return string that represents details of adding this Todo task.
     */
    @Override
    public String execute() {
        int minCommandLength = 4;
        if (input.length() == minCommandLength) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("todo"));
        }
        Todo t = new Todo(input.substring(minCommandLength + 1), new String[0]);
        return taskList.addTask(t);
    }

}
