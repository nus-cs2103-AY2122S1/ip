package duke.task;

/**
 * A wrapper for a Todos which is a task without date/time
 *
 * @author Wong Yun Rui Chris
 */
public class ToDo extends Task {

    /**
     * A public constructor to initialise a Todos task.
     *
     * @param description The String description/name of the task
     * @param isDone The Boolean of if the task is done
     */
    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of this ToDo task that is to be displayed
     * by Duke. It comprises the tag for ToDo, description of this ToDo and the
     * ToDo of this task.
     *
     * @return The string representation of this ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the data representation for this ToDo task that is to be saved
     * and used on initial execution of Duke.
     *
     * @return The String representation of the data of this ToDo task
     */
    @Override
    public String toData() {
        return "[T] | " + super.toData();
    }
}
