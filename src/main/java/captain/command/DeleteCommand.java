package captain.command;

import captain.DukeException;
import captain.Storage;
import captain.Ui;
import captain.task.Task;
import captain.task.TaskList;

public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTaskById(taskId);
        tasks.deleteTask(task);
        storage.saveTasks(tasks);
        return ui.showDeleteTask(tasks, task);
    }
}
