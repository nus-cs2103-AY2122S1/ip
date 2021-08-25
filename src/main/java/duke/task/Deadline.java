package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a task that should be completed by a specific time.
 */
public class Deadline extends Task {
    private final LocalDateTime dateTime;

    public Deadline(boolean done, String name, LocalDateTime dateTime) {
        super(done, name);
        this.dateTime = dateTime;
    }

    /**
     *
     * Turn Deadline into a string for saving to memory.
     *
     * @return concise string representation of Deadline.
     */
    @Override
    public String encode() {
        return String.format("D|%s|%s", super.encode(), dateTime);
    }

    /**
     * Turn task into a human readable string form.
     *
     * @return string representation of Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return String.format("[D]%s (by: %s)", super.toString(), this.dateTime.format(dateTimeFormatter));
    }
}
