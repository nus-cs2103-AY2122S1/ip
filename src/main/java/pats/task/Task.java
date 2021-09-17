package pats.task;

import static java.util.Objects.requireNonNull;

public abstract class Task {
    private static final int NUM_OF_SAVE_PARAMS = 4;

    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        assert description != null : "description is null";
        this.description = description;
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

    // getters

    public static int getNumOfSaveParams() {
        return NUM_OF_SAVE_PARAMS;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    // setter

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    // string methods

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task into a string to store in local file.
     *
     * The fields are divide by "|"; second parameter is 1 if the task is done, 0 otherwise.
     * @return a string to store in local file that represents the task.
     * @see pats.Parser#fileContentsToTask(String)
     */
    public abstract String populateSaveData();
}
