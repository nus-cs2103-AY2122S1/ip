public class Event extends Task{
    private String taskDate;

    public Event(String taskName, String taskDate, boolean isDone) {
        super(taskName, TaskType.EVENT, isDone);
        this.taskDate = taskDate;
    }

    public Event(String taskName, String taskDate) {
        this(taskName, taskDate, false);
    }

    @Override
    public String toSaveFormat() {
        return String.format("E, %d, %s, %s", isDone() ? 1 : 0, getTaskName(), taskDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), taskDate);
    }
}
