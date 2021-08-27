package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime time;

    /**
     * Constructor for a deadline.
     *
     * @param description description of the task
     * @param time the deadline in datetime format for the task
     */
    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }


    /**
     * Convert the deadline into the specific string for data saving.
     *
     * @return the string representation in the specific format for database
     */
    @Override
    public String stringifyTask() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.description,
                this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }


    /**
     * Return the string representation of the deadline.
     *
     * @return string representation of the deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by %s)", super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}