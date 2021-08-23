public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }
}
