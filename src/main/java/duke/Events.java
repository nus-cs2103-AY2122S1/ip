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
        String prefix = "[E] [ ] ";
        if (super.isDone()) {
            prefix = "[E] [X] ";
        }
        return (prefix + super.getName() + " (at: " + time.format(formatter) + ")");
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
        if (super.isDone()) {
            prefix = "E|1|";
        }
        string.append(prefix).append(super.getName()).append("|").append(time);
        return string.toString();
    }

}
