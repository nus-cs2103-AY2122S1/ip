package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.Priority;

/**
 * A class encapsulating a Event variant of a Task.
 *
 * @author Toh Wang Bin
 */
public class Events extends Task {

    //The duration during which the specified Task occurs.
    private LocalDate time;

    /**
     * Constructor for a Event instance.
     *
     * @param name A String describing the Task.
     * @param time A LocalDate instance describing the duration of the Task.
     */
    public Events(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    /**
     * Returns the String representation of the Task instance.
     *
     * @return A String representing the Task instance.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String prefix = "[E] [ ] ";
        if (super.isDone()) {
            prefix = "[E] [X] ";
        }
        String returnString = prefix + super.getName() + " (at: " + time.format(formatter) + ")";
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
        String prefix = "E|0|";
        String priorityLevel = "none";
        if (super.isDone()) {
            prefix = "E|1|";
        }
        if (getPriorityLevel() != null) {
            priorityLevel = Priority.toDataString(getPriorityLevel());
        }
        string.append(prefix).append(super.getName()).append("|").append(time).append("|").append(priorityLevel);
        return string.toString();
    }

}
