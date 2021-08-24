public class DeleteCommand extends Command {
    private final int taskToDelete;

    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.remove(taskToDelete);
        storage.getFullContents(tasks);
        ui.show("\tNoted. I've removed this task: \n\t\t" +
                tasks.retrieveTask(taskToDelete - 1),
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
