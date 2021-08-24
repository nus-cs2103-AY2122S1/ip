public class AddCommand extends Command {
    private Task toAddTask;

    public AddCommand(Task toAddTask) {
        this.toAddTask = toAddTask;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, FileManager fileManager) {
        tasks.add(this.toAddTask);
        fileManager.updateTaskList(tasks, ui);
        ui.taskAdded(this.toAddTask, tasks.getNumTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
