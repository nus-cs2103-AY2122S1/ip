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
    }

    /**
     * Constructs a ToDo task from an existing task description and completion status.
     * Used when loading from a save file.
     *
     * @param completed String indicating the status of completion: 1 if done, 0 if not.
     * @param description String of the task description.
     */
    public ToDo(String completed, String description) {
        super(description);

        if (completed.equals("1")) {
            super.markTaskAsDone();
        }
    }

    @Override
    public String saveAsString() {
        return super.formatString("T");
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}