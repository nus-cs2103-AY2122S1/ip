package captain.command;

import captain.DukeException;
import captain.Storage;
import captain.Ui;
import captain.task.Task;
import captain.task.TaskList;

public class AddCommand<T extends Task> extends Command {

    private final T taskToAdd;

    public AddCommand(T taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(taskToAdd);
        storage.saveTasks(tasks);
        return ui.showAddTask(tasks, taskToAdd);
    }
}
