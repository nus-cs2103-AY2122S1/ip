package duke;

/**
 *  This class represents the tasks in a list.
 *  Also provides some operations on tasks.
 *
 * @author Ryan Tian Jun.
 */
public class Task {
    private String description;
    private boolean isDone;
    private Type type;

    // ToDos, DeadLines, Events, Others(Placeholder)
    protected enum Type {
        T, D, E, O
    }

    /**
     * This constructor receives new tasks from the command line.
     *
     * @param description Task description.
     * @param type Task type, refer to enum TYPE.
     */
    public Task(String description, Type type) {
        this.description = description;
        this.isDone = false;
        if (type == Type.T) {
            this.type = Type.T;
        } else if (type == Type.D) {
            if (description.contains("/by")) {
                this.description = description.substring(0, description.indexOf("/by"));
            }
            this.type = Type.D;
        } else if (type == Type.E) {
            if (description.contains("/at")) {
                this.description = description.substring(0, description.indexOf("/at"));
            }
            this.type = Type.E;
        } else {
            this.type = Type.O;
        }
    }

    /**
     * This constructor receives tasks from stored file (history).
     *
     * @param type Task type, refer to enum TYPE.
     * @param isDone Whether the task has been done.
     * @param description Task description.
     */
    public Task(Type type, boolean isDone, String description) {
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


    /**
     * Returns Task description.
     *
     * @return String representation of description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns if Task has been done.
     *
     * @return True, Task has been done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns Task Type.
     *
     * @return Returns Type of the task.
     */
    public Type getType() {
        return this.type;
    }

    // return task type
    private String printType() {
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

    /**
     * Returns String representation of a Task.
     * Does not include timeline/deadlines, which are handled
     * in the respective subclasses.
     *
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return printType() + getStatusIcon() + getDescription();
    }
}
