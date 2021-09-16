package duke.task;

import java.time.LocalDate;

/**
 * Task class represents a task that Duke keep tracks of and it contains the description and status of it.
 *
 * @author Chng Zi Hao
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Name of user's task.
     */
    public Task(String description) {
        assert description != "" : "Description is empty.";
        this.description = description;
        this.isDone = false;
    }

    public abstract boolean hasDate(LocalDate date);

    /**
     * Gets the String representation of Task's status.
     *
     * @return a String where "X" represent completed and " " represents uncompleted.
     */
    public String getStatusIcon() {
        String done = "X";
        String incomplete = " ";
        return (isDone ? done : incomplete); // mark done task with X
    }

    /**
     * Changes status of task such that it is mark as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Converts task to a suitable format to be placed in data.txt.
     *
     * @return Formatted String which represents a task to be placed in data.txt.
     */
    public String format() {
        return String.format("%s | %s", isDone ? "1" : "0", this.description);
    }

    /**
     * Checks if the description contains keyword substring.
     *
     * @param keyword Keyword to be checked against.
     * @return true if the description contains keyword substring, false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }
}
