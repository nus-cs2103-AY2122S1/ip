public class Task {
	// Constants
	private static final String PREFIX_NOT_DONE = " [ ] ";
	private static final String PREFIX_DONE = " [X] ";

	// Constants/Variables related to the instance
	private final String name;
	private boolean done = false;

	/**
	 * Constructor for a Task instance.
	 *
	 * @param name Name of the task to be created.
	 */
	public Task(String name) {
		this.name = name;
	}

	/**
	 * Marks a task as done.
	 */
	public void setDone() {
		if (done) {
			HelpBot.reply("You've already done this task, stop bothering me!");
			return;
		}
		HelpBot.reply("About time you did your work, you lazy bum! I GUESS I'll mark it as done for you:\n"
				+ "  [X] " + this.name);
		done = true;
	}

	/**
	 * Returns a String representation of the Task instance.
	 *
	 * @return String representation of the Task instance.
	 */
	@Override
	public String toString() {
		if (!done) {
			return PREFIX_NOT_DONE + this.name;
		}
		return PREFIX_DONE + this.name;
	}
}
