package duke.task;

/**
 * Represents the abstract concept of Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected enum Priority {
        HIGH, MEDIUM, LOW;
    }
    protected Priority priority;

    /**
     * Represents a general Task concept.
     * @param description for general Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.markAsMediumPriority();
    }

    /**
     * Gets the description of a Task.
     *
     * @return Description String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Determines if the Task is Done.
     *
     * @return true or false
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the Status Icon of a Task.
     *
     * @return "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }


    /**
     * Marks a Task as Done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Marks a Task as High Priority.
     */
    public void markAsHighPriority() {
        this.priority = Priority.HIGH;
    }

    /**
     * Marks a Task as Medium Priority.
     */
    public void markAsMediumPriority() {
        this.priority = Priority.MEDIUM;
    }

    /**
     * Marks a Task as Low Priority.
     */
    public void markAsLowPriority() {
        this.priority = Priority.LOW;
    }

    public boolean isHighPriority() {
        return this.priority.equals(Priority.HIGH);
    }

    public boolean isMediumPriority() {
        return this.priority.equals(Priority.MEDIUM);
    }

    public boolean isLowPriority() {
        return this.priority.equals(Priority.LOW);
    }

    @Override
    public String toString() {
        String result = "[" + this.getStatusIcon() + "] ";
        if (isHighPriority()) {
            result += "!! ";
        } else if (isMediumPriority()) {
            result += "!  ";
        } else {
            result += " ";
        }
        result += this.description;
        return result;
    }
}
