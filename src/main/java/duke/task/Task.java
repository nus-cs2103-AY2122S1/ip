package duke.task;


public abstract class Task {

    public static final String SAVE_DATA_MARKER = "|";

    private String name;
    private boolean isDone = false;

    /**
     * Constructor for a Task object.
     * @param name Task name.
     * @param isDone Whether or not task is done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public Task(String name) {
        this.name = name;
    }

    /**
     * Returns a character based on whether or not Task is done.
     * @return 'X' if Task is done, ' ' otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the Task as 'done'.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the Task when saved to a file.
     * @return String to be written into file when task list is saved.
     */
    public String formatForFile() {
        char doneStatus = this.isDone ? '1' : '0';
        return SAVE_DATA_MARKER + doneStatus + SAVE_DATA_MARKER + this.name;
    }

    /**
     * Returns the name of the task.
     * @return name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a String representation of the Task object.
     * @return Task name and whether or not Task is done.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
