public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(this.task);
        ui.displayAddedMessage(this.task, taskList);
    }
}
