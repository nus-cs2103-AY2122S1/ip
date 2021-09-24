package duke;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime date;

    /**
     * Constuctor for event.
     *
     * @param event User inputted event.
     * @param date User inputted date of event.
     */
    public Event(String event, LocalDateTime date) {
        super(event, "E", date);
        this.date = date;
    }

    /**
     * Formats details of Event task into one string.
     *
     * @return String representation of a deadline task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (at: %s)",
                this.getTaskSymbol(), super.toString(), dateToString(this.date));
    }
}
