public class DeleteCommand extends Command {
    private final int index;
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("Looks like there is no such task to be deleted");
        }
        Task task = tasks.deleteTask(index);
        int len = tasks.size();
        String message = String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.",
                task.toString(),
                len,
                len <= 1 ? "task" : "tasks");
        System.out.println(message);
    }
}
