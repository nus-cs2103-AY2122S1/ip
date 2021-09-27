package duke.task;

/**
 * A class represents a task that is created by a user through Duke. It contains information about the details of the
 * task.
 */
public class Task {
    protected final String taskDescription;
    protected boolean isDone;

    /**
     * Constructs an instance of a task with the given description of the task
     *
     * @param taskDescription a string literal of the description of the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Indicates that this task has been completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns true if the description of the task contains a specified pattern within it, and false otherwise.
     *
     * @param pattern a string of the keyword to be checked against the description.
     * @return true if pattern is contained within task description.
     */

    public boolean hasKeyWord(String pattern) {
        return this.taskDescription.contains(pattern);
    }

    /**
     * Returns a string that represents a serialized store format of the task that is specific to Duke.
     *
     * @return a string of serialized format.
     */
    public String toDukeStoreFormat() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.taskDescription);
    }

    /**
     * Returns a string that shows the details of the task in a standardized format.
     *
     * @return a string of task details.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', taskDescription);
    }
}
