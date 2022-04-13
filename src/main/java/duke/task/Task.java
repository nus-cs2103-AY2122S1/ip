package duke.task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a task.
 */
public abstract class Task {
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");

    /**
     * Description of the task
     */
    private final String description;
    /**
     * Checks if the task has been completed
     */
    private boolean isDone;

    /**
     * Constructor for `Task`.
     *
     * @param isDone Indicates if the `Task` has been done.
     * @param description Name for the `Task`.
     */
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    private String isDone() {
        return (this.isDone ? "X" : "  ");
    }

    /**
     * Returns false if current `Task` is done. Otherwise, mark current `Task` as done and return true.
     *
     * @return Whether the `Task` has been successfully marked as done.
     */
    public boolean markAsDone() {
        if (isDone) {
            return false;
        } else {
            isDone = true;
            return true;
        }
    }

    /**
     * Checks if Task contains a certain keyword in its description.
     *
     * @param keyword The keyword.
     * @return True is task contains keyword in its description. Returns false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone(), this.description);
    }

    /**
     * Returns the String format that represent the `Task` object when saved as data.
     *
     * @return The String representation of `Task` object in data.
     */
    public String saveAsData() {
        return isDone + "\n" + description;
    }

    /**
     * Checks if the current `Task` has already been added to the list.
     *
     * @param list List of `Task` objects.
     * @return True if already added. False otherwise.
     */
    public abstract boolean checkIfAlreadyAdded(ArrayList<Task> list);

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Task) {
            Task o = (Task) obj;

            return this.description.equals(o.description);
        }

        return false;
    }
}
