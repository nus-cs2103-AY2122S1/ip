public class DeleteCommand extends Command {
    private final int taskIndex;
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskIndex);
        ui.showDelete(task, tasks);
    };
}
