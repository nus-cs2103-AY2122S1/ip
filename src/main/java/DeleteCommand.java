public class DeleteCommand extends Command {
    private String[] input;

    public DeleteCommand(String[] input) {
        this.input = input;
    }

    @Override
    public void execute(Tasklist tasks, Storage storage, Ui ui) {
        Task t = tasks.deleteTask(this.input);
        if (t != null) {
            storage.modifySave(tasks.getList());
            ui.deleteResponse(t, tasks);
        }
    }
}
