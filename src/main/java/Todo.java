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
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
