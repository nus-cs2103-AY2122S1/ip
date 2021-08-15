public class AddCommand implements Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        Duke.tasks[Duke.count++] = this.task;
        Message.print(new String[] {
                "Got it. I have added this task:",
                "\t" + task.toString(),
                String.format("Now you have %d task%s in your list", Duke.count, Duke.count > 1 ? "s" : "")});
    }
}
