public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.task);
        ui.displayAddedMessage(this.task, tasks);
    }
}
