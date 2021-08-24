
import java.time.LocalDate;

public class Event extends Task {
    private LocalDate dateAndTime;
    /**
     * Constructor for Event
     *
     * @param description is the string of the description of the given task
     */
    public Event(String description, String dateAndTime) {
        super(description, "event");
        this.dateAndTime = DateHandler.formatDate(dateAndTime);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (On: " + DateHandler.convertDate(this.dateAndTime) + ")";
    }
}