package poseidon.task;

import java.time.LocalDateTime;

/**
 * Represents an {@code Todo} object that contains the description of the task.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} object with the given description.
     *
     * @param description Description of the {@code Todo} object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new {@code Todo} object with the given description and done status.
     *
     * @param description Description of the {@code Todo} object.
     * @param isDone Done status of the {@code Todo} object.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }

    @Override
    public String toStorage() {
        return "T%" + isDone + "%" + description + "\n";
    }

    @Override
    protected LocalDateTime getDateTime() {
        return LocalDateTime.MAX;
    }
}
