package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime date;

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
                super.getTaskSymbol(), super.toString(),dateToString(this.date));
    }
}
