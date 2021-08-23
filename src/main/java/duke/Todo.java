package duke;

/**
 * A type of task that just keeps track of the description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns To do's String form.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the task type of duke.Todo.
     *
     * @return 0 to represent duke.Todo task type.
     */
    @Override
    public int taskType() {
        return 0;
    }

    /**
     * Returns the description in the format that will be saved into Hard drive.
     *
     * @return String
     */
    @Override
    public String savedFormat() {
        return super.description;
    }
}
