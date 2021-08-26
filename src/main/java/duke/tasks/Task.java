package duke.tasks;

import java.time.LocalDateTime;

/**
 *  The task class is an abstraction of a task
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime date;

    /**
     * public constructor which initialises the description of a task
     * @param description description of a task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public int getIntStatus() {
        return (isDone ? 1 : 0);
    }

    public String getDetails() {
        return this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDateString() {
        return this.date.toString();
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}

