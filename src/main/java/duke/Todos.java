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
     * Return the String representation of the Task instance.
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

    public String toDataString() {
        StringBuilder string = new StringBuilder();
        if (isDone) {
            string.append("T|1|").append(super.name);
            return string.toString();
        } else {
            string.append("T|0|").append(super.name);
            return string.toString();
        }
    }
}
