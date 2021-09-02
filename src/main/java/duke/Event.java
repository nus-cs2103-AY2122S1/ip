package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    protected String at;
    protected LocalDate date;

    /**
     * Constructor for an event.
     *
     * @param description a description of the event.
     * @param at the date the event is happening.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        String[] d = at.split("-");
        if (d.length == 3) {
            date = LocalDate.parse(at);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (this.date != null) {
            return "[E]" + super.toString() + " (at: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + this.at + ")";
        }
    }
}
