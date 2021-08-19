/**
 * Todo is a subclass of a Task.
 * It has the same behaviour of a task except printing out the Task type when using
 * ToString method call.
 */

public class Todo extends Task{
    /**
     * Constructor for Todo object.
     *
     * @param description the task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns out the Task description and the task type in String
     *
     * @return the String representation of a Todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
