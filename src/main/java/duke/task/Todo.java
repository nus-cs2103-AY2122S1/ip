package duke.task;

public class Todo extends Task {
    private static final char TASK_LETTER = 'T';

    /**
     * Constructs a todo task.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task. It is used to instantiate a todo that is already marked as done.
     *
     * @param description The description of the task.
     * @param isDone Whether todo is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[%c]%s", Todo.TASK_LETTER, super.toString());
    }

    @Override
    public String stringToStore() {
        return Todo.TASK_LETTER + " | " + this.getStatusIcon() + " | " + this.description + "\n";
    }
}
