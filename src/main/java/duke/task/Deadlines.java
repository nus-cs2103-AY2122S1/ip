package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.Priority;

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
        String prefix = "[D] [ ] ";
        if (super.isDone()) {
            prefix = "[D] [X] ";
        }
        String returnString = prefix + super.getName() + " (by: " + deadline.format(formatter) + ")";
        if (getPriorityLevel() != null) {
            returnString += Priority.getPriorityString(getPriorityLevel());
        }
        return returnString;
    }

    /**
     * Returns a string representation of the Task instance optimised for
     * saving in the file.
     *
     * @return A String representing the Task instance.
     */
    public String toDataString() {
        StringBuilder string = new StringBuilder();
        String prefix = "D|0|";
        String priorityLevel = "none";
        if (super.isDone()) {
            prefix = "D|1|";
        }
        if (getPriorityLevel() != null) {
            priorityLevel = Priority.toDataString(getPriorityLevel());
        }
        string.append(prefix).append(super.getName()).append("|").append(deadline).append("|").append(priorityLevel);
        return string.toString();
    }

}
