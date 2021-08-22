import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This class is a subclass of task.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */
public class Event extends Task{
    protected LocalDate date;
    private String time;

    /**
     * The construction method for an event.
     *
     * @param description detail of an event
     * @param date time of a deadline
     */
    public Event(String description, LocalDate date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * The toString method to output an event.
     *
     * @return return the string form of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Task.dateFormat(date) + " " + time + ")";
    }

    /**
     * The method is to get type of the event
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * The method is to get time of the event
     */
    @Override
    public String getTime() {
        return date.toString() + " | " + time;
    }
}
