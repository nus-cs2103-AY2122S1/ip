public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean isDone, boolean hasNotif) {
        super(description, isDone);
        this.by = by;
        this.category = Category.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
