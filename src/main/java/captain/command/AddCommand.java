package captain.command;

import captain.DukeException;
import captain.Storage;
import captain.Ui;
import captain.task.Task;
import captain.task.TaskList;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.saveTasks(tasks);
        return ui.showAddTask(tasks, task);
    }
}
