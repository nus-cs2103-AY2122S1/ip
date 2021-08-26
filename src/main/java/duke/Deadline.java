package duke;

/**
 * Deadline class that extends tasks. Should come with a name for the task as well as a deadline for the task
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDateTime deadline;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

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

}
