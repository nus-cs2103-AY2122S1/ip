public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks);
        int len = tasks.size();
        String message = String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                task.toString(),
                len,
                len <= 1 ? "task" : "tasks");
        ui.reply(message);
    }
}
