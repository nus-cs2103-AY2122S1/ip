package duke.operation;

/**
 * This is the Task class to handle all tasks.
 */
public class Task implements Comparable<Task> {
	protected String description;
	protected boolean isDone;
	protected Command taskType;

	public Command getTaskType() {
		return taskType;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * Constructor for Task objects.
	 *
	 * @param description input string
	 */
	public Task(String description, boolean isDone) {
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

	@Override
	public int compareTo(Task otherTask) {
		Command thisTaskType = this.getTaskType();
		Command otherTaskType = otherTask.getTaskType();

		switch (thisTaskType) {
		case TODO: {
			return -1;
		}
		case EVENT: {
			if (otherTaskType.equals(Command.TODO)) {
				return 1;
			}
			if (otherTaskType.equals(Command.DEADLINE)) {
				return 1;
			}
		}
		case DEADLINE: {
			if (otherTaskType.equals(Command.TODO)) {
				return 1;
			}
			if (otherTaskType.equals(Command.EVENT)) {
				return -1;
			}
		}
		default: {
			return 0;
		}
		}
	}
}