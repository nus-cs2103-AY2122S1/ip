import java.time.LocalDate;

public class Event extends Task {
    protected String at;
    protected LocalDate atDate;

    public Event(String description, String at, boolean isDone, boolean hasNotif) {
        super(description, isDone);
        this.at = at;
        this.category = Category.EVENT;
    }

    public Event(String description, LocalDate atDate, boolean isDone, boolean hasNotif) {
        super(description, isDone);
        this.atDate = atDate;
        this.category = Category.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + (at != null ? at : atDate) + ")";
    }
}
