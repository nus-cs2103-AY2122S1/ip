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
    /**
     * Constructs a new ToDo task from the given description.
     *
     * @param description String of the task description.
     */
    public ToDo(String description) {
        super(description);

        assert description.trim().isEmpty() : "ToDo was created with empty description";
    }

    /**
     * Constructs a ToDo task from an existing task description and completion status.
     * Used when loading from a save file.
     *
     * @param completedStatus String indicating the status of completion: 1 if done, 0 if not.
     * @param description String of the task description.
     */
    public ToDo(String completedStatus, String description) {
        super(description);
        assert description.trim().isEmpty() : "ToDo was created with empty description";
        assert completedStatus.matches("1|0") : String.format("Incorrect completedStatus %s", completedStatus);

        if (completedStatus.equals("1")) {
            super.markTaskAsDone();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToString() {
        return super.formatString("T");
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
