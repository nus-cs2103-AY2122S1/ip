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

    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s (by: %s)",
                this.type.charAt(0),
                this.isCompleted() ? "X" : "",
                this.getTaskSummary(),
                this.deadline
        );
    }
}
