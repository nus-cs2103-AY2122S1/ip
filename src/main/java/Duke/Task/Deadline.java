package Duke.Task;

/**
 * This class represents a deadline - a task that needs to be
 * done before a specific date/time.
 */
public class Deadline extends Task {
    public static final String IDENTIFIER = "D";
    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String getType() {
        return IDENTIFIER;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date);
    }
}
