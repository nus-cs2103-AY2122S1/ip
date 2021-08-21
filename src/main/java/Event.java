import java.time.LocalDate;

public class Event extends Task {
    private LocalDate eventDate;
    /**
     * Constructor for the Deadline task.
     * @param name Name of the Task.
     * @param eventDate Date of the Event.
     */
    public Event(String name, LocalDate eventDate) {
        super(name);
        this.eventDate = eventDate;
    }

    /**
     * Retrieves the date of the event.
     * @return The date of the event.
     */
    public String getDate() {
        return eventDate.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate.getDayOfMonth() + " "
                + eventDate.getMonth() + " " + eventDate.getYear() + ")";
    }
}