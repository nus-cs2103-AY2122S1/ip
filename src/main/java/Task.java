public class Task {
	private String description;
	private boolean isDone;
	
	public Task(String description) {
		this.description = description;
		isDone = false;
	}
	
	/**
	 * Marks the task as done.
	 */
	public void markAsDone() {
		isDone = true;
	}
	
	@Override
	public String toString() {
		return "[" + (isDone ? "X" : " ") + "] " + description;
	}
}
