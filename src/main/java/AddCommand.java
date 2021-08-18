public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public String execute(TaskManager taskManager) {
        return "Got it. I've added this task:\n\t" + taskManager.addTask(this.task)
                + "\nNow you have " + taskManager.getNumOfTasks() + " tasks in the list.";
    }
}