package duke.task;

/**
 * A class representing a task which does not have a time associated with it
 */
public class Todo extends Task {
    /** The symbol that represents a deadline */
    private static final String symbol = "[T]";

    /**
     * Constructor of the todo task.
     *
     * @param action The task that has to be completed.
     */
    public Todo(String action) {
        super(action);
    }

    /**
     * returns the state of the Deadline in a concise manner suitable for saving.
     *
     * @return A concise format of the state of the Deadline
     */
    @Override
    public String toSaveFormat() {
        return String.format("%s||%s||%s||%s", symbol, super.getTag(), super.isComplete(), super.getAction());
    }

    /**
     * Returns a string representing the task
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("%s%s", symbol, super.toString());
    }

    /**
     * Indicate if another object is 'equal' to this object.
     *
     * @param obj Reference object with which to compare to.
     * @return true if they are equal.
     *         false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo otherTodo = (Todo) obj;
            return otherTodo.toSaveFormat().equals(this.toSaveFormat());
        }
        return false;
    }
}
