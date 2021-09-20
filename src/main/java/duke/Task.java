package duke;

/**
 * Parent task class as abstraction for ToDo, Deadline, Event.
 *
 * @author Ren Weilin
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Marks this tasks as completed.
     */
    public String markAsDone() {
        if (!isDone) {
            isDone = true;
            return "Nice! I've marked this task as done:\n" + this.toString();
        }
        return "Task has already been marked as complete!";
    }

    /**
     * Helper method for toString(), returns X or Space depending on completion status.
     *
     * @return String "X" or " " for complete or incomplete task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

