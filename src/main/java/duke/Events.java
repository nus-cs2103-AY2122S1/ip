package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
     * @oaram time A LocalDate instance describing the duration of the Task.
     */
    public Events(String name,LocalDate time) {
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
        if (isDone) {
            return ("[E] [X] " + name  + " (at: " + time.format(formatter).toString() + ")");
        } else {
            return ("[E] [ ] " + name  + " (at: " + time.format(formatter).toString() + ")");
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
        if (isDone) {
            string.append("E|1|").append(super.name).append("|").append(time);
            return string.toString();
        } else {
            string.append("E|0|").append(super.name).append("|").append(time);
            return string.toString();
        }
    }
}
