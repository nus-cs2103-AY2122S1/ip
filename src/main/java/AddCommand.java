public class AddCommand implements Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        Duke.tasks[Duke.count++] = this.task;
        Message.print("Added: " + task.getContent());
    }
}
