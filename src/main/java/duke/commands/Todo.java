package duke.commands;

/**
 * Encapsulates a task that is a Todo.
 * Similar to a task, a Todo has a description.
 *
 * @author Jason Ng
 * @version Duke Level-8
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo task.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description, "T");
    }

    /**
     * Returns string representation of a Todo task.
     * Method override from toString() of parent class Task.
     *
     * @return string representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Marks task as done, and returns string representation of Todo task being marked done.
     * Method override from markDone() of parent class Task.
     *
     * @return string representation of Todo task being marked done.
     */
    @Override
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [T][X] %s", this.description);
    }
}
