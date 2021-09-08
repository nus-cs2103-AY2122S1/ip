package duke.command;

import duke.task.TaskList;
import duke.task.TimedToDo;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the command equivalent of the Timed Todo task.
 */
public class TimedTodoCommand extends Command {
    private TimedToDo timedTodo;

    protected TimedTodoCommand(String remainingText) throws DukeException {
        timedTodo = TimedToDo.createNewTimedTodo(remainingText, false);
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
        assert timedTodo != null : "timed todo should not be null";
        return taskList.addTask(timedTodo);
    }
}
