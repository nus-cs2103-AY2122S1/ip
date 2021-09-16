package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A class representing a task that need to be completed by a certain time.
 */
public class Deadline extends Task {
    /** The symbol that represents a deadline */
    private static final String symbol = "[D]";
    /** The deadline of the task */
    private final LocalDateTime deadline;


    /**
     * The constructor of a deadline task.
     *
     * @param action Task to be completed by the deadline.
     * @param deadline The time when the task should be completed
     */
    public Deadline(String action, LocalDateTime deadline) {
        super(action);
        this.deadline = deadline;
    }

    /**
     * returns the state of the Deadline in a concise manner suitable for saving.
     *
     * @return A concise format of the state of the Deadline
     */
    @Override
    public String toSaveFormat() {
        return String.format("%s||%s||%s||%s||%s", symbol, super.getTag(), super.isComplete(),
                super.getAction(), this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }

    /**
     * Returns a string representing the task
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", symbol, super.toString(),
                this.deadline.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)));
    }
}
