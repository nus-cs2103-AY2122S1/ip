package duke;

/**
 * Encapsulates a general task.
 */
abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Identifies whether the task is completed or not.
     *
     * @return "X" if it is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void complete() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    @Override
    public int compareTo(Task otherTask) {
        return this.description.compareTo(otherTask.description);
    }

    /**
     * Formats the task into a string that is compliant with the save file format.
     *
     * @return a string representing the task to be saved in save file
     */
    public abstract String saveData();
}
