public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String time = this.deadline.length() > 0 ? (" (at: " + this.deadline + ")") : "";

        return "[D]" + super.toString() + time;
    }
}
