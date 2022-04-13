package duke.task;

import duke.Priority;

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
        String returnString = prefix + super.getName();
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
        String prefix = "T|0|";
        String priorityLevel = "none";
        if (super.isDone()) {
            prefix = "T|1|";
        }
        if (getPriorityLevel() != null) {
            priorityLevel = Priority.toDataString(getPriorityLevel());
        }
        string.append(prefix).append(super.getName()).append("|").append(priorityLevel);
        return string.toString();
    }
}
