package duke.task;

import static java.util.Objects.requireNonNull;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    // factory methods
    public static ToDo getToDo(String description) {
        requireNonNull(description, "Description of ToDo cannot be null");
        return new ToDo(description);
    }

    public static ToDo getToDo(String description, boolean isDone) {
        requireNonNull(description, "Description of ToDo cannot be null");
        return new ToDo(description, isDone);
    }

    public static Deadline getDeadline(String description, String dueTime) {
        requireNonNull(description, "Description of deadline cannot be null");
        return new Deadline(description, dueTime);
    }

    public static Deadline getDeadline(String description, String dueTime, boolean isDone) {
        requireNonNull(description, "Description of deadline cannot be null");
        return new Deadline(description, dueTime, isDone);
    }

    public static Event getEvent(String description, String period) {
        requireNonNull(description, "Description of event cannot be null");
        return new Event(description, period);
    }

    public static Event getEvent(String description, String period, boolean isDone) {
        requireNonNull(description, "Description of event cannot be null");
        return new Event(description, period, isDone);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task into a string to store in local file.
     *
     * The fields are divide by "|"; second parameter is 1 if the task is done, 0 otherwise.
     * @return a string to store in local file that represents the task.
     * @see duke.Parser#fileContentsToTask(String)
     */
    public String populateSaveData() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }
}
