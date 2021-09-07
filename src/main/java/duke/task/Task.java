package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This class is to create a task.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task
     *
     * @param description detail of the task to be created
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns task status
     *
     * @return string form of the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Prints a task
     *
     * @return string form of the task
     */
    @Override
    public String toString() {
        String s = "[" + getStatusIcon() + "] " + this.description;
        return s;
    }

    /**
     * Finishes a task
     *
     * @return string form of the task after complete it
     */
    public String finished() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Finishes the task.
     */
    public void setFinish() {
        this.isDone = true;
    }

    /**
     * Gets type of the task
     */
    public String getType() {
        return "";
    }

    /**
     * Gets description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets time of the task
     */
    public String getTime() {
        return "";
    }

    /**
     * Returns deadline/time of a task
     *
     * @return LocalDate of the task
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Converts a LocalDate instance to String format.
     *
     * @param date intaks a LocalDate
     * @return string of the input
     */
    protected static String toDateFormat(LocalDate date) {
        return date.getMonth() + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    /**
     * Converts a LocalDateTime instance to String format.
     *
     * @param time intaks a LocalDateTime
     * @return string of the input
     */
    protected static String toTimeFormat(LocalDateTime time) {
        return time.getMonth() + " " + time.getDayOfMonth() + " " + time.getYear() + " " + time.toLocalTime();
    }

    public boolean isDone() {
        return isDone;
    }
}
