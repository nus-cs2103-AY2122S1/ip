public class Deadline extends Task {
    private final String byWhen;

    public Deadline(String taskName, String byWhen) {
        super(taskName);
        this.byWhen = byWhen;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byWhen + ")";
    }
}
