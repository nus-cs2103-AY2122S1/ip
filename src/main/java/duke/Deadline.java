package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that extends tasks. Should come with a name for the task as well as a deadline for the task
 */
public class Deadline extends Task {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline task
     * @param name name of task
     * @param deadline Use to indicate the deadline of the task
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }


    /**
     * String representation of the Deadline class
     * @return String. See above
     */
    public String toString() {
        return "[D]" + super.toString() + " " + formatter.format(deadline);
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }
}
