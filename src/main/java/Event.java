import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Class for event tasks.
 */
public class Event extends Task{

    private final LocalDate atTime;

    /**
     * Constructor for an event task.
     *
     * @param description String describing the event task.
     * @param at String describing the time of the event.
     * @throws DukeException Thrown if time could not be parsed.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.category = TaskType.event;
        try {
            this.atTime = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Could not parse time input.");
        }
    }

    /**
     * Get string representation of event task.
     *
     * @return String describing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +  " (at: "
            + atTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String getTime() {
        return at;
    }
}
