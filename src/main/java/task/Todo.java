package task;

/**
 * task.Todo is a subclass of a task.Task.
 * It has the same behaviour of a task except printing out the task.Task type when using
 * ToString method call.
 */

public class Todo extends Task {
    /**
     * Constructor for task.Todo object.
     *
     * @param description the task description
     */
    public Todo(String description, String notes, boolean completed) {
        super(description, notes, completed);
    }

    /**
     * Returns out the task.Task description and the task type in String
     *
     * @return the String representation of a task.Todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s\n%s", super.toString(), this.notes);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String getDeadline() {
        return "";
    }
}
