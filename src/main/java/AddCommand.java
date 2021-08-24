public class AddCommand extends Command {
    private TaskType type;
    private String[] input;

    public AddCommand(TaskType t, String[] inputSplit) {
        this.type = t;
        this.input = inputSplit;
    }

    @Override
    public void execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        Task t = tasks.addTask(this.type, this.input);
        storage.appendSave(t);
        ui.addResponse(t, tasks.size());
    }
}
