package duke.task;

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
     * @param input the description of the event task.
     * @return new Event with updated description and due date.
     */
    @Override
    public Event update(String input) {
        if (input.contains("/at")) {
            String[] splitInput = input.split(" /at ", 2);
            return new Event(splitInput[0], splitInput[1]);
        } else {
            return new Event(input, this.at);
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

    /**
     * Returns the date of the event.
     *
     * @return the value of at.
     */
    public String getAt() {
        return this.at;
    }
}
