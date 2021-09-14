package catobot.item;

import java.time.format.DateTimeFormatter;

import catobot.exception.EmptyCommandException;

/**
 * Represents items which can be done.
 */
public class Task implements Comparable<Task> {
    public static final DateTimeFormatter DATE_FORMAT_DOC =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter DATE_FORMAT_DISPLAY =
            DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    /** Details of the task. */
    private final String description;
    /** Status of whether the task is done. */
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The task description.
     * @throws EmptyCommandException if the description is empty.
     */
    public Task(String description) throws EmptyCommandException {
        if (description.isEmpty()) {
            throw new EmptyCommandException("task");
        }
        this.description = description;
        this.isDone = false;
        assert !this.description.equals("") : "description of a task cannot be empty";
    }

    private String getStatusIcon() {
        return (isDone ? "X" : "  "); // mark done task with X
    }

    /**
     * Marks the Task as Done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks if the task contains a keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the task description contains the keyword, false otherwise.
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Represents Task in the format of string.
     *
     * @return The string representation of Task, including status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Represents the format of task in storage.
     *
     * @return The string representation of task in storage.
     */
    protected String toStringInDoc() {
        return String.format("%s | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Compare two tasks lexicographically.
     *
     * @param o Another task being compared with.
     * @return -1 if the description is lexicographically less than the other,
     *         1 if the description is lexicographically greater than the other,
     *         0 if the description are the same.
     */
    @Override
    public int compareTo(Task o) {
        return description.compareTo(o.description);
    }
}
