public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand (int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) {
        Task removed = tasklist.delete(index);
        ui.notifySuccessfulDelete(tasklist, removed);
        ui.readCommand();
    }
}
