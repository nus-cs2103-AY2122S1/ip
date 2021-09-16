package poseidon.task;

import java.time.LocalDateTime;

public class Todo extends Task {

    /**
     * Constructs a new Todo object with the given description.
     *
     * @param description Description of the Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo object with the given description and done status.
     *
     * @param description Description of the Todo object.
     * @param isDone Done status of the Todo object.
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
