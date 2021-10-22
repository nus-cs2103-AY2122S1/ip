package duke.classes.tasks;

/**
 * General Task class containing Task-wide methods
 */
public class Task {
    protected enum Type {
        T,
        D,
        E,
    }

    protected String description;
    protected boolean isDone;
    protected Type type;

    /**
     * Class Constructor
     *
     * @param description String Description of the Task
     * @param type Type of the Task, ([T]oDo, [D]eadline, [E]vent)
     */
    public Task(String description, Type type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Class Constructor taking in if the task is done
     *
     * @param description String Description of the Task
     * @param type Type of the Task, ([T]oDo, [D]eadline, [E]vent)
     * @param done boolean indicating if the task is done
     */
    public Task(String description, Type type, boolean done) {
        this.type = type;
        this.description = description;
        this.isDone = done;
    }

    /**
     * Completes the Task
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + type.toString() + "]" + getStatusIcon() + description;
    }

    /**
     * Returns the task as a string format to be saved in the localList file
     *
     * @return Task as a String in the appropriate format
     */
    public String toFileString() {
        return type.toString() + " " + doneString() + " " + description;
    }

    /**
     * Returns the status of the task as a string
     *
     * @return String of the task status
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Returns X if the task is done and O if the task is undone
     *
     * @return X or O
     */
    public String doneString() {
        return (isDone ? "X" : "O");
    }

    /**
     * Returns True if desc Contains str
     *
     * @param str String to search for in desc
     * @return boolean indicating presence of str
     */
    public boolean descContains(String str) {
        return description.contains(str);
    }
}
