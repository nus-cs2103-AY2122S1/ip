package duke;

/**
 * duke.Task list logic abstracted from duke.Duke.
 *
 * @author Ren Weilin
 * @version 19 August 2021
 */


public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        if (!isDone) {
            isDone = true;
            System.out.println("  Nice! I've marked this task as done:");
            System.out.println("    " + this);
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

