package duke.task;

/**
 * The ToDo Class represents a task without any date/time attached to it.
 *
 * It contains information relating to the task:
 * - description
 * - isDone
 *
 * @author Benedict Chua
 */
public class ToDo extends Task {
    private static final String TODO_IDENTIFIER = "T";
    /**
     * Constructs a new ToDo task from the given description.
     *
     * @param description String of the task description.
     */
    public ToDo(String description) {
        super(description);

        assert !description.trim().isEmpty() : "ToDo was created with empty description";
    }

    /**
     * Constructs a ToDo task from an existing task description and completion status.
     * Used when loading from a save file.
     *
     * @param completionStatus String indicating the status of completion: 1 if done, 0 if not.
     * @param description String of the task description.
     */
    public ToDo(CompletionStatus completionStatus, String description) {
        super(description);
        assert !description.trim().isEmpty() : "ToDo was created with empty description";

        if (completionStatus.equals(CompletionStatus.COMPLETED)) {
            super.markTaskAsDone();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToString() {
        return super.formatString(TODO_IDENTIFIER);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TODO_IDENTIFIER, super.toString());
    }
}
