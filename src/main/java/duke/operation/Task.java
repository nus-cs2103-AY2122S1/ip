package duke.operation;

/**
 * This is the Task class to handle all tasks.
 */
public class Task {
	protected String description;
	protected boolean isDone;

	/**
	 * Constructor for Task objects.
	 *
	 * @param description input string
	 */
	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	/**
	 * Gets status icon of a task for printing purpose.
	 *
	 * @return String representation of status
	 */
	public String getStatusIcon() {
		return (isDone ? "[X]" : "[ ]"); // mark done task with X
	}

	/**
	 * Marks a task as done.
	 */
	public void doneTask() {
		this.isDone = true;
	}

	@Override
	public String toString() {
		return this.getStatusIcon() + " " + this.description;
	}
}