package duke.command;

import duke.task.TaskList;
import duke.task.ToDo;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the command equivalent of the Todo task.
 */
public class TodoCommand extends Command {
    protected static final String COMMAND = "todo";
    private ToDo todo;

    protected TodoCommand(String remainingText) throws DukeException {
        todo = ToDo.createNewTodo(remainingText);
    }

    /**
     * Executes the command.
     *
     * @param taskList The taskList keeping track of the tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert todo != null : "todo should not be null";
        return taskList.addTask(todo);
    }
}
