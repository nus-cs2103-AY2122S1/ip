public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(task);
        int len = tasks.size();
        String message = String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                task.toString(),
                len,
                len <= 1 ? "task" : "tasks");
        System.out.println(message);
    }
}
