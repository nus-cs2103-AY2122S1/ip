package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Encapsulates a task with a description and whether has it been completed.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;
    protected LocalTime time;
    protected LocalDate date;

    /**
     * Public constructor to initialize a Task object.
     *
     * @param description The description of the Task.
     */
    public Task(String description, LocalTime time, LocalDate date) {
        this.description = description;
        this.time = time;
        this.date = date;
        this.isDone = false;
    }

    @Override
    public int compareTo(Task t) {
        int temp = this.getDate().compareTo(t.getDate());
        if (temp == 0) {
            return this.getTime().compareTo(t.getTime());
        }
        return temp;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as done with a "X", if it is not done it will be just a blank space.
     *
     * @return A string showing whether a task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markTaskDone() {
        this.isDone = true;
    }

    /**
     * Returns the output of deadline in a format suitable for saving to a file.
     *
     * @return A string representing the output.
     */
    public String outputFormat() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    //...
}
