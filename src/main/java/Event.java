import java.time.LocalDateTime;

/**
 * Event class.
 * Used to represent a event task.
 *
 * @author KelvinSoo
 * @version Level-4
 *
 */
public class Event extends Task {
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(String description, LocalDateTime localDateTime) {
        super(description);
        this.eventTime = String.format("%s of %s %s, %s%s",
                localDateTime.getDayOfMonth(),
                localDateTime.getMonth().toString(),
                localDateTime.getYear(),
                localDateTime.getHour() < 12 ? localDateTime.getHour() : localDateTime.getHour() - 12,
                localDateTime.getHour() < 12 ? "am" : "pm");
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return String.format("%s (at: %s)", super.getDescription(), this.eventTime);
    }
}
