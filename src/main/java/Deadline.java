public class Deadline extends DateDependentTask {
    public Deadline(String description, String by) {
        super(description, by);
    }

    protected String getShortForm() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + super.getDate() + ")";
    }
}
