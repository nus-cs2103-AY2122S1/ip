public class DoneCommand extends Command {
    String[] input;

    public DoneCommand(String[] input) {
        this.input = input;
    }

    @Override
    public void execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        Task t = tasks.doneTask(input);
        if (t != null) {
            storage.modifySave(tasks.getList());
            ui.doneResponse(t);
        }
    }
}
