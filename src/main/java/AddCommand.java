public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public String execute(TaskManager taskManager) {
        return "added: " + taskManager.addTask(this.task);
    }
}