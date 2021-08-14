/**
 * This Todo class implements the characteristics of a task
 * without any date or time attached to it.
 *
 * @author Yeo Jun Wei
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo instance that takes in a description.
     *
     * @param description The given task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a Todo instance.
     *
     * @return A string representing a Todo instance.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
