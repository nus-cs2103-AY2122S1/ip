public class DeleteCommand extends Command {
    private int index;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    DeleteCommand(int index, TaskList tasks, Ui ui, Storage storage) {
        this.index = index;
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    protected void execute() {

        Task task = tasks.get(index);
        tasks.remove(index);
        storage.save(tasks);
        ui.delete(task, tasks);
    }

}
