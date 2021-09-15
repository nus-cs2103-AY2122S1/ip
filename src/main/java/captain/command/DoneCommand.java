package captain.command;

import captain.DukeException;
import captain.Storage;
import captain.Ui;
import captain.task.Task;
import captain.task.TaskList;

public class DoneCommand extends Command {
    private int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTaskById(taskId);
        task.setDone(true);
        storage.saveTasks(tasks);
        return ui.showTaskDone(task);
    }

}
