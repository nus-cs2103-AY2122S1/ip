package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor method for a Task object.
     *
     * @param description the name of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean checkDescription(String input) {
        return description.contains(input);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true; // mark this task as done
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
