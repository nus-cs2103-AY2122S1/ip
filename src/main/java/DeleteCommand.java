public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.displayDeleteMessage(tasks, index);
        tasks.deleteTask(index);
    }
}
