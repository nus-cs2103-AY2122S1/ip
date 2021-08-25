package duke.task;

/**
 * class to represent tasks
 *
 */
public class Task {

	protected String title;
	protected Boolean done;

	Task(String title) {
		this.title = title;
		this.done = false;
	}

	Task(String title, Boolean done) {
		this.title = title;
		this.done = done;
	}

	/** function to mark this task as done */
	public void markAsDone() {
		this.done = true;
	}

	/**
	 * Returns string representation of the task for displaying
	 *
	 * @return string representation of the task for displaying
	 */
	@Override
	public String toString() {
		if (this.done) {
			return "[X] " + this.title;
		} else {
			return "[ ] " + this.title;
		}
	}

	/**
	 * Returns a string representation of the task for saving
	 *
	 * @return string representation of the task for saving
	 */
	public String saveString() {
		if (this.done) {
			return "Task : 1 : " + this.title;
		} else {
			return "Task : 0 : " + this.title;
		}
	}
}
