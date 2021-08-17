public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getFormattedDeadline() {
        return "( by: " + deadline + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + getFormattedDeadline();
    }
}
