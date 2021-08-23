package task;

public class Event extends Task {
    private static final String TYPE = "E";
    private String date;

    public Event(String description) {
        super(description);
    }

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    public String getLabel() {
        return TYPE;
    }

    @Override
    public String databaseString() {
        return TYPE + " | " + super.databaseString() + " | "
                + date;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", getLabel(), getStatusIcon(), this.description, this.date);
    }
}
