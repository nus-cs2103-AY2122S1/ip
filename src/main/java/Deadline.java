public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String by) {
        super(description);
        this.deadline = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
