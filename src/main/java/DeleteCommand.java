public class DeleteCommand extends Command {
    public DeleteCommand(String[] args) {
        super(args);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(Integer.valueOf(args[0]));
        storage.writeToFile(tasks);
        ui.showDeletedTask(task, tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}
