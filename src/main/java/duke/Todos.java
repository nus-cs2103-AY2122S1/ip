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
        String prefix = "[T] [ ] ";
        if (super.isDone()) {
            prefix = "[T] [X] ";
        }
        return prefix + super.getName();
    }

    /**
     * Returns a string representation of the Task instance optimised for
     * saving in the file.
     *
     * @return A String representing the Task instance.
     */
    public String toDataString() {
        StringBuilder string = new StringBuilder();
        String prefix = "T|0|";
        if (super.isDone()) {
            prefix = "T|1|";
        }
        string.append(prefix).append(super.getName());
        return string.toString();
    }
}
