public class Deadline extends Task{
    private String taskDate;

    public Deadline(String taskName, String taskDate, boolean isDone) {
        super(taskName, "Deadline", false);
        this.taskDate = taskDate;
    }

    public Deadline(String taskName, String taskDate) {
        this(taskName, taskDate, false);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), taskDate);
    }
}
