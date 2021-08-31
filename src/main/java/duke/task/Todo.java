package duke.task;

/**
 * A wrapper for a Todos which is a task without date/time
 *
 * @author Wong Yun Rui Chris
 */
public class Todo extends Task{

    /**
     * A public constructor to initialise a Todos task.
     *
     * @param description The String description/name of the task
     * @param isDone The Boolean of if the task is done
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of this Todo task that is to be displayed
     * by Duke. It comprises the tag for Todo, description of this Todo and the
     * Todo of this task.
     *
     * @return The string representation of this Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the data representation for this Todo task that is to be saved
     * and used on initial execution of Duke.
     *
     * @return The String representation of the data of this Todo task
     */
    @Override
    public String toData() {
        return "[T] | " + super.toData();
    }
}
