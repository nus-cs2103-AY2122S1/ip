package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    private LocalDate eventDate;

    /**
     * Creates an Event Object.
     *
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.eventDate = LocalDate.parse(at);
        this.at = at;
    }

    /**
     * Returns the character representing the task type.
     *
     * @return Character representing task type.
     */
    @Override
    public char getTaskType() { return 'E'; }

    /**
     * Returns the date of the event as a String.
     *
     * @return Date of the event.
     */
    @Override
    public String getTime() {
        return this.at;
    }

    /**
     * Returns the string representation of an Event Object.
     *
     * @return String representation of Event Object.
     */
    @Override
    public String toString() {
        String formattedDate = this.eventDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "  [E]" + super.toString() + " (at: " + formattedDate + ")";
    }
}
