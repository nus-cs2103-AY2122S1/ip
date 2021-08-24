package duke;

public class Task {
    private final String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    /**
     * Marks the task as completed.
     */
    public void setCompleted() {
        this.completed = true;
    }

    /**
     * Returns the name of the task.
     * @return the name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the completion status of the task.
     * @return a boolean representing whether the task is completed or not
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * This returns the string format the task is represented in the duke file.
     * @return the string representing a task in the duke file
     */
    public String print() {
        return String.format("T,%d,%s",isCompleted() ? 1 : 0, this.getName());
    }

    /**
     * Returns the string representation of the Task object.
     * @return the string representation of the Task object.
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.isCompleted() ? "[X]" : "[ ]", this.getName());
    }
}
