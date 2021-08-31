package duke;

/**
 * A class encapsulating a Todo variant of a Task.
 *
 * @author Toh Wang Bin
 */
public class Todos extends Task {

    /**
     * Constructor for a Todo instance.
     *
     * @param name A String describing the Task.
     */
    public Todos(String name) {
        super(name);
    }

    /**
     * Returns the String representation of the Task instance.
     *
     * @return A String representing the Task instance.
     */
    @Override
    public String toString() {
        if (super.isDone()) {
            return ("[T] [X] " + super.getName());
        } else {
            return ("[T] [ ] " + super.getName());
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
            string.append("T|1|").append(super.getName());
            return string.toString();
        } else {
            string.append("T|0|").append(super.getName());
            return string.toString();
        }
    }
}
