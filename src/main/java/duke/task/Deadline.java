package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a specific type of task that contains the description of the task.
 */
public class Deadline extends Task {
    private static final char TASK_LETTER = 'D';
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructs a deadline task.
     *
     * @param description The description of the task.
     * @param date The deadline date.
     * @param time The deadline time.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Constructs a deadline task. It is used to instantiate a deadline that is already marked as done.
     *
     * @param description The description of the ask.
     * @param date The deadline date.
     * @param time The deadline time.
     * @param isDone Whether the deadline is done or not.
     */
    public Deadline(String description, LocalDate date, LocalTime time, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns string representation of a deadline.
     *
     * @return A string representing the deadline.
     */
    @Override
    public String toString() {
        return String.format("[%c]%s (by: %s %s)", Deadline.TASK_LETTER,
                super.toString(), this.date.format(Deadline.DATE_TIME_FORMATTER), this.time);
    }

    /**
     * Convert the deadline to a string that can be saved to a file and converted back to itself.
     *
     * @return The string to be stored.
     */
    @Override
    public String stringToStore() {
        return Deadline.TASK_LETTER + " | " + this.getStatusIcon() + " | " + this.description + " | "
                + this.date.format(Deadline.DATE_TIME_FORMATTER) + " | " + this.time + "\n";
    }
}