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
    public ToDo(String description) {
        super(description);
    }

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