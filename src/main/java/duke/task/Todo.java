package duke.task;

public class Todo extends Task {

    /**
     * A constructor for this todo Task.
     *
     * @param description the description of what the task is.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a to-do task based on the given description.
     *
     * @param input the string containing the to-do task description.
     * @return the to-do task constructed from the given description.
     */
    public static Task setTodo(String input) {
        Task todo = new Todo(input);
        return todo;
    }

    /**
     * Returns the string representation of this Task, which follows the following format:
     * [T][Task status] Task Description
     *
     * @return string representation of this Task, which is the type of task (Todo),
     *         its status, and its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
