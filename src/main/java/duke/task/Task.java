package duke.task;

public class Task {
    /** The name of the Task. */
    private String name;
    /** Whether the Task has been completed. */
    private boolean completed;

    /**
     * Constructs a new Task.
     *
     * @param name The name of the Task.
     */
    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.completed = true;
    }

    /**
     * Converts the Task to the format used for saving in the storage file on the user's computer.
     *
     * @return The save format of the Task.
     */
    public String toSaveFormat() {
        String completedStr = completed ? "1" : "0";
        return completedStr + "|" + name;
    }

    /**
     * Converts the Task to its string representation.
     * 
     * @return The string representation of the Task.
     */
    @Override
    public String toString() {
        String symbol = completed ? "X" : " ";
        return "[" + symbol + "] " + name;
    }
}
