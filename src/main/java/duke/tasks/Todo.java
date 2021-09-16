package duke.tasks;

import duke.commands.EditCommand;

/**
 * This class represents a Todo Task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo Task with the given description that is not marked as done.
     *
     * @param description Description of the Todo Task.
     */
    public Todo(String description) {
        super(description, false);
    }

    /**
     * Constructs a Todo Task with the given description that can be marked as done.
     *
     * @param description Description of the Todo Task.
     * @param isDone Whether task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }

    /**
     * {@inheritdoc}
     * Appends "[T]" to the front to show that Task is a Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            return super.equals(obj);
        }
        return false;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Task getUpdatedTask(EditCommand edit) {
        String newDescription = edit.getDescription() == null ? description : edit.getDescription();
        return new Todo(newDescription, isDone);
    }
}
