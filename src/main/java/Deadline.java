public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String deadline, boolean completed) {
        super(description, completed);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDeadline() {
        return this.deadline;
    }
}
