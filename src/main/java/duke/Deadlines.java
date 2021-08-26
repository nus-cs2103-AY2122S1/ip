package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class encapsulating a Deadline variant of a Task.
 *
 * @author Toh Wang Bin
 */
public class Deadlines extends Task {

    //The time by which the Task should be completed, i.e. the deadline.
    private LocalDate deadline;

    /**
     * Constructor for a Deadline instance.
     *
     * @param name A String describing the Task.
     * @param deadline A String describing the deadline of the Task.
     */
    public Deadlines(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Return the String representation of the Task instance.
     *
     * @return A String representing the Task instance.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        if (isDone) {
            return ("[D] [X] " + name  + " (at: " + deadline.format(formatter).toString() + ")");
        } else {
            return ("[D] [ ] " + name  + " (at: " + deadline.format(formatter).toString() + ")");
        }
    }

    public String toDataString() {
        StringBuilder string = new StringBuilder();
        if (isDone) {
            string.append("D|1|").append(super.name).append("|").append(deadline);
            return string.toString();
        } else {
            string.append("D|0|").append(super.name).append("|").append(deadline);
            return string.toString();
        }
    }
}
