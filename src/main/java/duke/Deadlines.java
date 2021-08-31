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
     * Returns the String representation of the Task instance.
     *
     * @return A String representing the Task instance.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        if (super.isDone()) {
            return ("[D] [X] " + super.getName() + " (at: " + deadline.format(formatter) + ")");
        } else {
            return ("[D] [ ] " + super.getName() + " (at: " + deadline.format(formatter) + ")");
        }
    }

    /**
     * Returns a string representation of the Task instance optimised for
     * saving in the file.
     *
     * @return A String representing the Task instance.
     */
    public String toDataString() {
        StringBuilder string = new StringBuilder();
        if (super.isDone()) {
            string.append("D|1|").append(super.getName()).append("|").append(deadline);
            return string.toString();
        } else {
            string.append("D|0|").append(super.getName()).append("|").append(deadline);
            return string.toString();
        }
    }
}
