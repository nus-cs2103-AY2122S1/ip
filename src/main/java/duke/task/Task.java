package duke.task;

/**
 * Represents a Task the user wants to complete.
 * A task object has a name, and can be done or not.
 */
public abstract class Task {
	private final String name;
	private boolean isDone = false;

	/**
	 * Constructs a new Task with the given name.
	 */
	public Task(String name) {
		this.name = name;
	}

	/**
	 * Returns whether the task is marked as done.
	 */
	public boolean isDone() {
		return isDone;
	}

	/**
	 * Set the task as done or not.
	 *
	 * @param isDone whether the task is done
	 */
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	/**
	 * Returns the string representation of the completeness of the task.
	 */
	public String getCheckBox() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

	@Override
	public String toString() {
		return getCheckBox() + this.name;
	}
}
