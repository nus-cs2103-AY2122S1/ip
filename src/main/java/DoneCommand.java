public class DoneCommand extends Command{
    private static final String DONE_MSG = "Well done.";

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(this.index);
        task.markAsDone();

        String message = DONE_MSG + "\n" + task.toString() + "\n" + tasks.getTaskCountString();

        ui.printResponse(message);

        return true;
    }
}