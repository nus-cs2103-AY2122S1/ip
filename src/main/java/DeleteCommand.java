public class DeleteCommand extends Command {
    private final int index;
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("Looks like there is no such task to be deleted");
        }
        Task task = tasks.deleteTask(index);
        storage.save(tasks);
        int len = tasks.size();
        String message = String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.",
                task.toString(),
                len,
                len <= 1 ? "task" : "tasks");
        ui.reply(message);
    }
}
