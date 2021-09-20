package duke.task;

/**
 * A type of task that just keeps track of the description.
 */
public class Todo extends Task {

    /**
     * Constructor.
     *
     * @param description details about the task.
     */
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
     * @return character T to represent duke.Todo task type.
     */
    @Override
    public char getTaskType() {
        return 'T';
    }

    /**
     * Returns the description in the format that will be saved into Hard drive.
     *
     * @return String.
     */
    @Override
    public String toSavedFormat() {
        return super.description;
    }
}
