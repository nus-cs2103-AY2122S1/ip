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
     * Updates the description and due date of the Event.
     *
     * @param description the description of the event task.
     * @param at the date the event is happening.
     * @return new Event with updated description and due date.
     */
    public Event update(String description, String at) {
        return new Event(description, at);
    }

    //    /**
    //     * Updates the date of the Event.
    //     *
    //     * @param at the due date of the task.
    //     * @return new Event with the updated event time.
    //     */
    //    public Event updateDate(String at) {
    //        return new Event(this.description, at);
    //    }

    /**
     * Updates the description of the Event.
     *
     * @param description the description of the task.
     * @return new Event with the updated description;
     */
    public Event updateDesc(String description) {
        return new Event(description, this.at);
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
