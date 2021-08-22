import java.time.LocalDateTime;

public class Event extends Task{
    private LocalDateTime startDate;

    public Event(String description, LocalDateTime startDate) {
        super(description);
        this.startDate = startDate;
    }

    @Override
    public String typeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + startDate + ")";
    }
}
