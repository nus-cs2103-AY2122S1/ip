public class DeleteCommand extends Command {
    private final Task task;
    private final int index;

    public DeleteCommand(Task task, int index) {
        this.task = task;
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, FileManager fileManager) throws DukeException {
        tasks.delete(this.index);
        ui.deleteTask(this.task);
        fileManager.updateTaskList(tasks, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
