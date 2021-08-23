import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by, boolean isDone, boolean hasNotif) {
        super(description, isDone);
        this.by = by;
        this.category = Category.DEADLINE;
    }

    public Deadline(String description, LocalDate byDate, boolean isDone, boolean hasNotif) {
        super(description, isDone);
        this.byDate = byDate;
        this.category = Category.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + (by != null ? by : byDate) + ")";
    }
}