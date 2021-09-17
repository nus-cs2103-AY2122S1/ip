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
    public static final String COMMAND_WORD = "TODO";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + " : adds a todo task.";
    public static final String COMMAND_FORMAT = "Format: todo <description> "
            + "(e.g todo complete work)\n";
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
     *
     * @return string that represents details of adding this Todo task.
     * @throws DukeException if task faces an error during execution.
     */
    @Override
    public String execute() throws DukeException {
        int minCommandLength = 6;
        if (input.length() < minCommandLength) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("todo"));
        }
        try {
            Todo t = new Todo(input.substring(minCommandLength - 1), new String[0]);
            return taskList.addTask(t);
        } catch (Exception e) {
            throw new DukeException(Ui.getTodoFormatMsg());
        }
    }

}
