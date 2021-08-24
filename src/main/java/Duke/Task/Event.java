package Duke.Task;

/**
 * This class represents a event - a task that starts at a specific date
 * and ends at a specific date.
 */
public class Event extends Task {
    public static final String IDENTIFIER = "E";
    private String date;

    public Event(String description, String date) {
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
        return String.format("[E]%s (at: %s)", super.toString(), this.date);
    }
}
