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
	public void markAsDone() { this.done = true; }

	/**
	 * Returns String representation of the task
	 *
	 * @return string representation of the task
	 */
	@Override
	public String toString() {
		if (this.done) {
			return "[X] " + this.title;
		} else {
			return "[ ] " + this.title;
		}
	}

	public String saveString() {
		if (this.done) {
			return "Task : 1 : " + this.title;
		} else {
			return "Task : 0 : " + this.title;
		}
	}
}
