package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Constuctor for deadline.
     *
     * @param deadline User inputted event.
     * @param date User inputted date of event.
     */
    public Deadline(String deadline, LocalDateTime date) {
        super(deadline, "D", date);
        this.date = date;
    }

    /**
     * Formats details of deadline task into one string.
     *
     * @return String representation of a deadline task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)",
                super.getTaskSymbol(), super.toString(), dateToString(this.date));
    }
}
