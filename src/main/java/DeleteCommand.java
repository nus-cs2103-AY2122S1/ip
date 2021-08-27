public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        if (tasks.getLength() < taskIndex || 0 >= taskIndex) {
            throw new DukeException("Invalid task index provided!");
        }
        Task removedTask = tasks.removeTask(taskIndex);
        storage.updateTasks(tasks);
        ui.showRemovedTask(removedTask, tasks);
    }
}
