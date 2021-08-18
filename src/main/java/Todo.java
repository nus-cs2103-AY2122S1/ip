public class Todo extends Task {

    /**
     * A constructor for this todo Task.
     *
     * @param description The description of what the task is.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this Task, which follows the following format:
     * [T][Task status] Task Description
     *
     * @return A string representation of this Task, which is the type of task (Todo),
     *         its status, and its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
