package duke.task;

public class Todo extends Task {
    /**
     * Constructor of a Todo task.
     *
     * @param title title of the Todo task
     */
    public Todo(String title) {
        super(title);
    }

    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    public String toFileString() {
        return String.format("T | %s", super.toFileString());
    }

    /**
     * Returns a String representation of a Todo task. Starts "[T]" to indicate that
     * it is a Todo task.
     *
     * @return String representation of a Todo.
     */
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }
}
