public class Deadline extends Task {
    private String deadline;

    Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String logo() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline + ")";
    }

    public String getDeadline() {
        return this.deadline;
    }
}
