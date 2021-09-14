package agent.tasks;

import agent.exceptions.EmptyTodoBodyException;

/**
 * Represents a completable <code>Task</code> that only has a description.
 *
 * @author kevin9foong
 */
public class ToDo extends Task {
    /**
     * Constructs an instance of <code>ToDo</code> which contains the given description.
     *
     * @param description used to define what the <code>ToDo</code> is regarding.
     * @throws EmptyTodoBodyException thrown when no description is provided.
     */
    public ToDo(String description) throws EmptyTodoBodyException {
        if (description == null || description.isEmpty()) {
            throw new EmptyTodoBodyException();
        } else {
            super.setDescription(description);
        }
    }

    /**
     * Constructs an instance of <code>ToDo</code> with the provided description
     * and completion status.
     *
     * @param description description of what the <code>ToDo</code> entails.
     * @param isDone      completion status of <code>ToDo</code>.
     */
    public ToDo(String description, boolean isDone) {
        super.setDescription(description);
        super.setIsDone(isDone);
    }

    @Override
    public String getTaskRepresentation() {
        return TaskType.TODO + " |;; " + super.getTaskRepresentation();
    }

    /**
     * Returns the <code>String</code> representation representing the task type,
     * completion status, and description of this <code>ToDo</code>.
     *
     * @return <code>String</code> representation of this <code>ToDo</code>.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
