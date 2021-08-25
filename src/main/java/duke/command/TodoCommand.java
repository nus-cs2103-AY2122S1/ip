package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import duke.exception.NoActionException;
import duke.exception.SaveFileException;

import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {
    String action;
    public TodoCommand(String action) {
        super(false);
        this.action = action;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoActionException, SaveFileException {
        if (action.length() == 0) {
            throw new NoActionException("Command 'todo' requires a task action");
        }
        Task newTask = new Todo(action);
        tasks.add(newTask);
        ui.showTaskAdded(newTask, tasks);
        storage.save(tasks);
    }
}
