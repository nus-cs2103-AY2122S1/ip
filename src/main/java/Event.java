import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class: For tasks that start at a specific time and ends at a specific time
 * e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event extends Task {
    private LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description, Type.EVENT);
        this.date = date;
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description + "(at: "
                + date.format(DateTimeFormatter.ofPattern("dd LLL yyyy hh:mm a")) + ")";
    }
}
