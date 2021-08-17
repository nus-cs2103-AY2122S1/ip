public class Deadline extends Task {
    protected String byWhen;

    public Deadline(String description, String byWhen) {
        super(description);
        this.byWhen = byWhen;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + byWhen + ")";
    }
}
