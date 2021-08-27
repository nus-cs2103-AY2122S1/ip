package duke.task;

public class Task {
    /** The name of the Task. */
    private String name;
    /** Whether the Task has been completed. */
    private boolean isCompleted;

    /**
     * Constructs a new Task.
     *
     * @param name The name of the Task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Converts the Task to the format used for saving in the storage file on the user's computer.
     *
     * @return The save format of the Task.
     */
    public String convertToSaveFormat() {
        String completedStr = isCompleted ? "1" : "0";
        return completedStr + "|" + name;
    }

    /**
     * Converts the Task to its string representation.
     * 
     * @return The string representation of the Task.
     */
    @Override
    public String toString() {
        String symbol = isCompleted ? "X" : " ";
        return "[" + symbol + "] " + name;
    }
}
