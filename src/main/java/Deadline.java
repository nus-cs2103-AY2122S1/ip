public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String stem = super.toString();
        return String.format("[D]%s (by: %s)", stem, this.deadline);
    }
}