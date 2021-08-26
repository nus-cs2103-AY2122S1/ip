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
        if (isDone) {
            return ("[T] [X] " + name);
        } else {
            return ("[T] [ ] " + name);
        }
    }

    /**
     * Returns a string representation of the Task instance optimised for
     * saving in the file.
     *
     * @return A String representing the Task instance.
     */
    public String toDataString() {
        StringBuilder str = new StringBuilder();
        if (isDone) {
            str.append("T|1|").append(super.name);
            return str.toString();
        } else {
            str.append("T|0|").append(super.name);
            return str.toString();
        }
    }
}
