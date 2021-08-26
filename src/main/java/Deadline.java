public class Deadline extends Task {
    public static final String TYPE = "Deadline";
    private String deadline;

    public static Deadline of(String taskSummary, String byDate) {
        return new Deadline(taskSummary, byDate);
    }

    public Deadline(String taskSummary, String byDate) {
        super(taskSummary);
        this.deadline = byDate;
    }

    public static String syntax() {
        return "deadline command syntax: \'deadline <task> /by <deadlineTime>\'";
    }

    @Override
    public String toStorageFormat() {
        return String.format(
            "%s | %d | %s | %s",
            this.taskTypeSymbol(), this.isCompleted() ? 1 : 0,this.getTaskSummary(), this.deadline
        );
    }

    private String taskTypeSymbol() {
        return Character.toString(Deadline.TYPE.charAt(0));
    }

    @Override
    public String toString() {
        return String.format(
            "[%s][%s] %s (by: %s)",
            this.taskTypeSymbol(),
            this.isCompleted() ? "X" : "",
            this.getTaskSummary(),
            this.deadline
        );
    }
}
