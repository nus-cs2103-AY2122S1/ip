public class Deadline extends Task {
    private String deadline;

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String result = "[D]" + super.toString();
        if (deadline != null) {
            result += " (by: " + this.deadline + ")";
        }
        return result;
    }
}
