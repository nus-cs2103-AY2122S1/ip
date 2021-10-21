package duke;

/**
 * A Task that can be added to the Task List
 * which has a description.
 */
public class Todo extends Task {

    /**
     * A public constructor to create a Todo Task.
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo.
     * @return the string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the
     * todo to be saved into the file.
     * @return the string representation of the
     *         todo to be saved into the file.
     */
    @Override
    public String toStore() {
        return "[T]" + super.toString();
    }

}
