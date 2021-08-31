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
        if (super.isDone()) {
            return ("[E] [X] " + super.getName() + " (at: " + time.format(formatter) + ")");
        } else {
            return ("[E] [ ] " + super.getName() + " (at: " + time.format(formatter) + ")");
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
            string.append("E|1|").append(super.getName()).append("|").append(time);
            return string.toString();
        } else {
            string.append("E|0|").append(super.getName()).append("|").append(time);
            return string.toString();
        }
    }
}
