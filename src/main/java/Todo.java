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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "[T] | " + super.toData();
    }
}
