public class Deadline extends Task{
    private String taskDate;

    public Deadline(String taskName, String taskDate, boolean isDone) {
        super(taskName, TaskType.DEADLINE, isDone);
        this.taskDate = taskDate;
    }

    public Deadline(String taskName, String taskDate) {
        this(taskName, taskDate, false);
    }

    @Override
    public String toSaveFormat() {
        return String.format("D, %d, %s, %s", isDone() ? 1 : 0, getTaskName(), taskDate);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), taskDate);
    }
}
