package duke.command;

import duke.task.TaskList;
import duke.task.ToDo;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class TodoCommand extends Command {
    protected static final String COMMAND = "todo";
    private final ToDo todo;

    protected TodoCommand(String remainingText) throws DukeException {
        todo = ToDo.newTodo(remainingText);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.displayMessage(taskList.addTask(todo));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
