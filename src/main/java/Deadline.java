public class Deadline extends Task {
    private final String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline.trim();
    }

    public Deadline(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline.trim();
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String getFileString() {
        return String.format("D | %s | %s", super.getFileString(), this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
