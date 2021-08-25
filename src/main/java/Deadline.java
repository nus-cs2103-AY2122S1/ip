public class Deadline extends Task {
    private String type;
    private String deadline;

    public static Deadline of(String taskSummary, String byDate) {
        return new Deadline(taskSummary, byDate);
    }

    public Deadline(String taskSummary, String byDate) {
        super(taskSummary);
        this.type = "Deadline";
        this.deadline = byDate;
    }
    private String taskTypeSymbol() { return Character.toString(this.type.charAt(0)); }
    public static String syntax() { return "deadline command syntax: \'deadline <task> /by <deadlineTime>\'"; }
    public String toStorageFormat() {
        return String.format("%s | %d | %s | %s",
                this.taskTypeSymbol(), this.isCompleted() ? 1 : 0,this.getTaskSummary(), this.deadline);
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
