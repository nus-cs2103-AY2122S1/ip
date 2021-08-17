public class AddCommand implements Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        TaskStorage.getInstance().add(this.task);
        Message.print(new String[] {
                "Got it. I have added this task:",
                "\t" + task.toString(),
                String.format("Now you have %d task%s in your list", TaskStorage.getInstance().getSize(), TaskStorage.getInstance().getSize() > 1 ? "s" : "")});
    }
}
