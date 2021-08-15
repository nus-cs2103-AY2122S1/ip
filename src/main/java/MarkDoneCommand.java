public class MarkDoneCommand implements Command{
    Task task;

    public MarkDoneCommand(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.setDone(true);
        Message.print(new String[] {"Nice, I have marked this task as done:", "\tDone: " + task.toString()});
    }
}
