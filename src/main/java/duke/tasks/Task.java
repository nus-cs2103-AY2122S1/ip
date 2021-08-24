package duke.tasks;

/**
 * Task created by duke.Duke
 */
abstract public class Task {

    String name;
    boolean isTaskDone = false;

    /**
     * default constructor for a new task
     * @param name task name
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Full constructor with all fields exposed
     * @param name task name
     * @param isTaskDone done state
     */
    public Task(String name, boolean isTaskDone) {
        this.name = name;
        this.isTaskDone = isTaskDone;
    }

    public String addMsg() {
        return "added: " + this.name;
    }

    public void markDone() {
        this.isTaskDone = true;
    }

    public String getName() {
        return this.name;
    }

    /**
     * json-like representation of object data
     * @return String representation of data
     */
    public abstract String serialize();
    // newline is the only? reliable way to divide data without escape characters

    public String toString() {
        return (this.isTaskDone ? "[X] " : "[ ] ")
                + this.name;
    }
}

