public class Deadline extends Task {
    private final String deadline;

    public Deadline(String description, String deadline) throws AisuException {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String ParseData() {
        return "D;;" + (this.isDone ? "1" : "0") + ";;" + this.description + ";;" + this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[DeadL] %s %s (by: %s)", this.getStatusIcon(), this.description, this.deadline);
    }
}
