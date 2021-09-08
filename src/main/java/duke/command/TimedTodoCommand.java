package duke.command;

import duke.task.TaskList;
import duke.task.TimedToDo;
import duke.task.ToDo;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class TimedTodoCommand extends Command {
    private TimedToDo timedTodo;

    protected TimedTodoCommand(String remainingText) throws DukeException {
        timedTodo = TimedToDo.createNewTimedTodo(remainingText, false);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert timedTodo != null : "todo should not be null";
        return taskList.addTask(timedTodo);
    }
}
