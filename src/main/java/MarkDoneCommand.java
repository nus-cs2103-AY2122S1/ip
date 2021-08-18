public class MarkDoneCommand extends Command {
    private final int taskNumber;

    public MarkDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    public String execute(TaskManager taskManager) {
        return "Nice! I've marked this task as done:\n\t"
                + taskManager.doTaskAtIndex(taskNumber);
    }
}
