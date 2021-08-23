public class Deadline extends Task{
    protected String deadline;

    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline);
    }

    @Override
    public String getData() {
        return String.format("D | %s | %s", super.getData(), this.deadline);
    }
}
