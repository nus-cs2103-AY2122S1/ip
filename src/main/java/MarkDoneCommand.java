class MarkDoneCommand extends Command{

    private Task task;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    MarkDoneCommand(Task task, TaskList tasks, Ui ui, Storage storage) {
        this.task = task;
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    protected void execute() {
        task.markAsDone();
        storage.save(tasks);
        ui.markDone(this.task);
    }
}
