class AddCommand extends Command {

    private Task task;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    protected AddCommand(Task task, TaskList tasks, Ui ui, Storage storage) {

        this.task = task;
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    protected void execute() {
        tasks.add(task);
        storage.save(tasks);
        ui.add(task, tasks);
    }
}
