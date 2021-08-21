package duke;

/**
 *  This class represents the tasks in a list.
 *
 * @author Ryan Tian Jun.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TYPE type;

    // ToDos, DeadLines, Events, Others(Placeholder)
    protected enum TYPE {
        T, D, E, O
    }

    /**
     * This constructor receives new tasks from the command line.
     *
     * @param description Task description.
     * @param type Task type, refer to enum TYPE.
     */
    public Task(String description, TYPE type) {
        this.description = description;
        this.isDone = false;
        if (type == TYPE.T) {
            this.type = TYPE.T;
        } else if (type == TYPE.D) {
            if (description.contains("/by")) {
                this.description = description.substring(0, description.indexOf("/by"));
            }
            this.type = TYPE.D;
        } else if (type == TYPE.E) {
            if (description.contains("/at")) {
                this.description = description.substring(0, description.indexOf("/at"));
            }
            this.type = TYPE.E;
        } else {
            this.type = TYPE.O;
        }
    }

    /**
     * This constructor receives tasks from stored file (history).
     *
     * @param type Task type, refer to enum TYPE.
     * @param isDone Whether the task has been done.
     * @param description Task description.
     */
    public Task(TYPE type, boolean isDone, String description) {
        this.type = type;
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Marks task as done.
     *
     * @return returns true if it was successful.
     */
    public boolean markAsDone() throws DukeException {
        if (!isDone) {
            isDone = true;
            return true;
        } else {
           throw new DukeException("Task has already been marked as done!");
        }
    }


    // return task description
    private String getDescription() {
        return description;
    }

    // return task type
    private String getType() {
        return "[" + type.toString() + "]";
    }

    // return task status
    private String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Returns time if task is Deadline or Event.
     *
     * @return String representation of time.
     */
    public String getTime() {
        return "";
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + getDescription();
    }
}
